package com.videoeditorsdk.android.app.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.annotation.MainThread
import ly.img.android.pesdk.backend.views.abstracts.ImgLyUIRelativeContainer
import com.videoeditorsdk.android.app.R
import ly.img.android.pesdk.annotations.OnEvent
import ly.img.android.pesdk.backend.model.state.manager.StateHandler
import ly.img.android.pesdk.backend.model.state.manager.StateHandler.StateHandlerNotFoundException
import ly.img.android.pesdk.backend.model.state.manager.StateHandlerContext
import ly.img.android.pesdk.backend.model.state.manager.stateHandlerResolve
import ly.img.android.pesdk.ui.model.state.UiStateMenu
import ly.img.android.pesdk.ui.panels.MenuToolPanel
import java.lang.RuntimeException

class MyRawAdsView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs), View.OnClickListener, StateHandlerContext {

    /*
     * This is an example of extending the SDK by creating a view extending from any `View`.
     * We strongly recommend to use the `MyAdsView` variant.
     */

    ///////////////////////// SDK STATE-HANDLER BINDING /////////////////////////
    override var stateHandler = try {
        if (isInEditMode) {
            StateHandler(context)
        } else {
            StateHandler.findInViewContext(context)
        }
    } catch (e: StateHandlerNotFoundException) {
        throw RuntimeException(e)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        stateHandler.registerSettingsEventListener(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stateHandler.unregisterSettingsEventListener(this)
    }

    ////////////////////////////// CUSTOM VIEW CODE //////////////////////////////


    private val menuState: UiStateMenu by stateHandlerResolve()

    init {
        inflate(context, R.layout.my_ads_view, this)
        this.setOnClickListener(this)
    }

    @MainThread
    @OnEvent(value = [UiStateMenu.Event.TOOL_STACK_CHANGED], ignoreReverts = true)
    internal fun onToolStackChanged() {
        if (menuState.currentPanelData.id == MenuToolPanel.TOOL_ID) {
            this.visibility = View.VISIBLE
        } else {
            this.visibility = View.GONE
        }
    }

    override fun onClick(v: View?) {
        Toast.makeText(context, "Banner Clicked", Toast.LENGTH_SHORT).show()
    }
}