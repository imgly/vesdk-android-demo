package com.videoeditorsdk.android.app.custom

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import ly.img.android.pesdk.ui.activity.VideoEditorActivity
import com.videoeditorsdk.android.app.R
import ly.img.android.pesdk.assets.filter.basic.ColorFilterAssetBW
import ly.img.android.pesdk.backend.filter.FilterAssetHatch
import ly.img.android.pesdk.backend.model.EditorSDKResult
import ly.img.android.pesdk.backend.model.state.FilterSettings
import ly.img.android.pesdk.backend.model.state.LoadSettings
import ly.img.android.pesdk.backend.model.state.manager.StateHandler
import ly.img.android.pesdk.ui.activity.EditorActivity

class MyEditorActivity : VideoEditorActivity() {

    private var myView: MyAdsView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myView = findViewById(R.id.my_ads_view)
        // do something with it if you need to
        
        Toast.makeText(this, "Toast from custom Activity", Toast.LENGTH_SHORT).show()
    }

    override fun onCloseClicked() {
        // Override Editor close behavior, to remove dialog
        val stateHandler = stateHandler
        val loadSettings = stateHandler[LoadSettings::class]
        val result = EditorSDKResult.Builder(EditorSDKResult.Status.CANCELED).also {
            it.setProduct(stateHandler.product)
            it.setSourceUri(loadSettings.source)
            it.setSettingsList(getStateHandler().createSettingsListDump())
        }
        setResult(result)
        finish()
    }

    override fun onExportStart(stateHandler: StateHandler) {
        // Change settings before starting export.
        stateHandler[FilterSettings::class].filter = ColorFilterAssetBW()
        super.onExportStart(stateHandler)
    }
}