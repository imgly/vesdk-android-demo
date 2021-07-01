package com.videoeditorsdk.android.app

import android.os.Parcel
import android.os.Parcelable
import java.util.concurrent.TimeUnit
import ly.img.android.pesdk.annotations.OnEvent
import ly.img.android.pesdk.annotations.StateEvents
import ly.img.android.pesdk.backend.model.state.TrimSettings

import ly.img.android.pesdk.backend.model.state.manager.EventTracker
import ly.img.android.pesdk.kotlin_extension.parcelableCreator
import ly.img.android.pesdk.utils.convert

@StateEvents class MyEventListener : EventTracker, Parcelable {
    constructor() : super()
    constructor(parcel: Parcel) : super(parcel)

    // Helps the user to set at maximum 30 seconds.
    @OnEvent(TrimSettings.Event.START_TIME, TrimSettings.Event.END_TIME)
    fun onChangedVideoLength(trimSettings: TrimSettings) {
        if (trimSettings.trimDurationInNanoseconds.convert(TimeUnit.NANOSECONDS, TimeUnit.SECONDS) > 30) {
            trimSettings.endTimeInNanoseconds = trimSettings.startTimeInNanoseconds + 30.convert(TimeUnit.SECONDS, TimeUnit.NANOSECONDS)
        }
    }

    companion object {
        @JvmField val CREATOR = parcelableCreator(::MyEventListener)
    }

}