package com.videoeditorsdk.android.app.custom

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import ly.img.android.pesdk.ui.activity.VideoEditorActivity
import com.videoeditorsdk.android.app.R
import ly.img.android.pesdk.ui.activity.EditorActivity

class MyEditorActivity : VideoEditorActivity() {

    private var myView: MyAdsView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myView = findViewById(R.id.my_ads_view)
        // do something with it if you need to
        
        Toast.makeText(this, "Toast from custom Activity", Toast.LENGTH_SHORT).show()
    }
}