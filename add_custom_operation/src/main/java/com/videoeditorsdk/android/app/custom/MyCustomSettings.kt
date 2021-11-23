package com.videoeditorsdk.android.app.custom

import android.os.Parcel
import ly.img.android.pesdk.annotations.ImglyEvents
import ly.img.android.pesdk.backend.model.state.manager.ImglySettings
import ly.img.android.pesdk.kotlin_extension.parcelableCreator

class MyCustomSettings @JvmOverloads constructor(parcel: Parcel? = null) : ImglySettings(parcel) {

    @ImglyEvents object Event {
        private const val CLASS = "MyCustomSettings"
        const val STATE_REVERTED = "$CLASS.STATE_REVERTED"
        const val ENABLE_DISABLE = "$CLASS.FILTER"
        const val INTENSITY = "$CLASS.INTENSITY"
    }

    var isEnabled by value(true, callOnChange = Event.ENABLE_DISABLE)

    var intensity by value(1.0f, callOnChange = Event.INTENSITY)

    companion object {
        // VERY IMPORTANT!!!
        @JvmField val CREATOR = parcelableCreator(::MyCustomSettings)
    }
}