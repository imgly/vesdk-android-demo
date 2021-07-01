package com.videoeditorsdk.android.app

import android.widget.Toast
import java.util.concurrent.TimeUnit
import ly.img.android.pesdk.annotations.OnEvent
import ly.img.android.pesdk.backend.model.state.TrimSettings
import ly.img.android.pesdk.ui.activity.EditorActivity
import ly.img.android.pesdk.ui.model.state.UiStateMenu
import ly.img.android.pesdk.ui.panels.VideoCompositionToolPanel

import ly.img.android.pesdk.utils.convert

class MyEditorActivity : EditorActivity() {

    override fun onSaveClicked() {
        val trimSettings = stateHandler[TrimSettings::class]
        if (trimSettings.trimDurationInNanoseconds.convert(TimeUnit.NANOSECONDS, TimeUnit.SECONDS) <= 30) {
            super.onSaveClicked()
        } else {
            stateHandler[UiStateMenu::class].openMainTool(VideoCompositionToolPanel.TOOL_ID) // Or VideoTrimToolPanel.TOOL_ID
            Toast.makeText(this, "Video length is too long, trim it to 30sec please.", Toast.LENGTH_LONG).show()
        }
    }


}
