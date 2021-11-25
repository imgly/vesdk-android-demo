package com.videoeditorsdk.android.app.custom

import android.opengl.GLES20
import android.util.Log
import ly.img.android.opengl.canvas.render

import ly.img.android.opengl.textures.GlFrameBufferTexture
import ly.img.android.opengl.textures.GlTexture
import ly.img.android.pesdk.annotations.OnEvent
import ly.img.android.pesdk.backend.model.state.manager.stateHandlerResolve
import ly.img.android.pesdk.backend.operator.rox.RoxGlOperation

import ly.img.android.pesdk.backend.operator.rox.models.Requested
import kotlin.math.min

open class MyCustomRoxOperation : RoxGlOperation() {

    // Inject you custom settings (or any other settings you need)
    private val myCustomSettings : MyCustomSettings by stateHandlerResolve()

    // Generate a frame buffer the result is rendered to.
    // SetupInit is called before glSetup() and is like initializing in glSetup()
    private val resultCacheTexture by SetupInit {
        GlFrameBufferTexture()
    }

    // Initialize your filter
    private val hatchFilterProgram by SetupInit(::GlProgramCustomFilter)

    override fun glSetup(): Boolean {
        // glSetup() is called initially when the operation is created.
        // You could use it to initialize everything you need for your OpenGL operations.
        // It is called from the thread with the OpenGL render context.
        // You can use raw OpenGL API calls here.

        Log.d(TAG, "GlVersion is: " + GLES20.glGetString(GLES20.GL_VERSION))
        return super.glSetup()
    }

    override fun doOperation(requested: Requested): GlTexture? {
        // This is the render part.
        // It is called from the thread with the OpenGL render context.
        // You can use raw OpenGL API calls here.


        if (myCustomSettings.isEnabled) {
            // Use this to get the result from the previous renderer, like requested.
            val sourceTexture = sourceTextureAsRequested(requested)
            /*
             * To change parameters (like size) of the source request, use this instead:
             *
             * requestSourceAsTexture(generateSourceRequest(request).also { request ->
             *     // Change request parameter...
             * })
             */
            resultCacheTexture.record(withSizeOf = sourceTexture) {
                // The render method can be used to blit pixel by pixel.
                hatchFilterProgram.setUseDynamicInput(sourceTexture.isExternalTexture)
                hatchFilterProgram.render {
                    it.setUniformImage(sourceTexture)
                    it.setDelta((min(requested.width, requested.height) / 60f))
                    it.setWidth(requested.width.toFloat())
                    it.setHeight(requested.height.toFloat())
                    it.setIntensity(myCustomSettings.intensity)
                }
            }

            return resultCacheTexture
        } else {
            // Just pass the source answer
            return sourceTextureAsRequested(requested)
        }


    }

    @OnEvent(MyCustomSettings.Event.ENABLE_DISABLE, MyCustomSettings.Event.INTENSITY)
    internal fun onParameterChanged() {
        // Mark as dirty if your parameter has changed.
        flagAsDirty()
    }

    /*
     * Percentage of memory use, if you need for example 2 textures set it to at least 2.0f
     */
    override val estimatedMemoryConsumptionFactor = 1.0f

    companion object {
        val TAG = "MyCustomRoxOperation"
    }
}