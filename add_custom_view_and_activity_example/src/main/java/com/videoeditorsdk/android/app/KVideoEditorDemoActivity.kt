package com.videoeditorsdk.android.app

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.videoeditorsdk.android.app.custom.MyEditorBuilder
import ly.img.android.pesdk.VideoEditorSettingsList
import ly.img.android.pesdk.assets.filter.basic.FilterPackBasic
import ly.img.android.pesdk.assets.font.basic.FontPackBasic
import ly.img.android.pesdk.assets.frame.basic.FramePackBasic
import ly.img.android.pesdk.assets.overlay.basic.OverlayPackBasic
import ly.img.android.pesdk.assets.sticker.animated.StickerPackAnimated
import ly.img.android.pesdk.assets.sticker.emoticons.StickerPackEmoticons
import ly.img.android.pesdk.assets.sticker.shapes.StickerPackShapes
import ly.img.android.pesdk.backend.model.EditorSDKResult
import ly.img.android.pesdk.backend.model.constant.OutputMode
import ly.img.android.pesdk.backend.model.state.LoadSettings
import ly.img.android.pesdk.backend.model.state.VideoEditorSaveSettings
import ly.img.android.pesdk.ui.activity.ExternalVideoCaptureBuilder
import ly.img.android.pesdk.ui.model.state.*
import ly.img.android.pesdk.ui.panels.item.PersonalStickerAddItem
import ly.img.android.pesdk.ui.utils.PermissionRequest
import ly.img.android.serializer._3.IMGLYFileWriter
import java.io.File
import java.io.IOException

class KVideoEditorDemoActivity : Activity(), PermissionRequest.Response {

    companion object {
        const val VIDEO_EDITOR_SDK_RESULT = 1
        const val CAMERA_AND_GALLERY_RESULT = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val openGallery = findViewById<Button>(R.id.openGallery)
        openGallery.setOnClickListener {
            openSystemGalleryToSelectVideo()
        }

        val captureVideo = findViewById<Button>(R.id.captureVideo)
        captureVideo.setOnClickListener {
            openSystemCameraToCaptureVideo()
        }
    }

    // Create an empty new SettingsList and apply the changes to this reference.
    // If you include our asset packs and use our UI you also need to add them to the UI,
    // otherwise they are only available to the backend (e.g. serialization)
    // See the specific feature sections of our guides if you want to know how to add your own assets.
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    private fun createVESDKSettingsList() = VideoEditorSettingsList()
        .configure<UiConfigMainMenu> {
            it.toolList.removeAll { item ->
                item.id == "imgly_tool_audio_overlay_options"
            }
        }
        .configure<UiConfigFilter> { it.setFilterList(FilterPackBasic.getFilterPack()) }
        .configure<UiConfigText> { it.setFontList(FontPackBasic.getFontPack()) }
        .configure<UiConfigFrame> { it.setFrameList(FramePackBasic.getFramePack()) }
        .configure<UiConfigOverlay> { it.setOverlayList(OverlayPackBasic.getOverlayPack()) }
        .configure<UiConfigSticker> {
            it.setStickerLists(
                PersonalStickerAddItem(),
                StickerPackEmoticons.getStickerCategory(),
                StickerPackShapes.getStickerCategory(),
                StickerPackAnimated.getStickerCategory()
            )
        }
        .configure<VideoEditorSaveSettings> {
            // Set custom editor video export settings
            it.setOutputToGallery(Environment.DIRECTORY_DCIM)
            it.outputMode = OutputMode.EXPORT_IF_NECESSARY
        }

    private fun openSystemGalleryToSelectVideo() {
        try {
            startActivityForResult(Intent(Intent.ACTION_PICK).also {
                it.setDataAndType(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,"video/*")
            }, CAMERA_AND_GALLERY_RESULT)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "No Gallery installed", Toast.LENGTH_LONG).show()
        }
    }

    private fun openSystemCameraToCaptureVideo() = ExternalVideoCaptureBuilder().startActivityForResult(
        this,
        CAMERA_AND_GALLERY_RESULT
    )

    private fun openEditor(inputSource: Uri?) {
        val settingsList = createVESDKSettingsList()
            .configure<LoadSettings> {
                it.source = inputSource
            }

        MyEditorBuilder(this)
            .setSettingsList(settingsList)
            .startActivityForResult(this, VIDEO_EDITOR_SDK_RESULT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        intent ?: return

        if (resultCode == RESULT_OK && requestCode == CAMERA_AND_GALLERY_RESULT) {
            // Open Editor with some uri in this case with an video selected from the system gallery.
            val selectedVideo = intent.data
            if (selectedVideo != null) {
                openEditor(selectedVideo)
            }
        } else if (resultCode == RESULT_OK && requestCode == VIDEO_EDITOR_SDK_RESULT) {
            // Editor has an result.
            val data = EditorSDKResult(intent)

            Log.i("VESDK", "Source video is located here ${data.sourceUri}")
            Log.i("VESDK", "Result video is located here ${data.resultUri}")

            // OPTIONAL: read the latest state to save it as a serialisation
            val lastState = data.settingsList
            try {
                IMGLYFileWriter(lastState).writeJson(
                    File(
                        Environment.getExternalStorageDirectory(),
                        "serialisationReadyToReadWithPESDKFileReader.json"
                    )
                )
            } catch (e: IOException) {
                e.printStackTrace()
            }

        } else if (resultCode == RESULT_CANCELED && requestCode == VIDEO_EDITOR_SDK_RESULT) {
            // Editor was canceled
            val data = EditorSDKResult(intent)

            val sourceURI = data.sourceUri
            // TODO: Do something with the source...
        }
    }


    // Important permission request for Android 6.0 and above, don't forget to add this!
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        PermissionRequest.onRequestPermissionsResult(requestCode, permissions, grantResults)
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    override fun permissionGranted() {}
    override fun permissionDenied() { /* TODO: The Permission was rejected by the user. The Editor was not opened, Show a hint to the user and try again. */ }
}
