package com.videoeditorsdk.android.app.custom

import android.app.Activity
import android.app.Fragment
import ly.img.android.pesdk.backend.model.state.manager.SettingsList
import ly.img.android.pesdk.ui.activity.EditorBuilder
import ly.img.android.pesdk.ui.activity.ImgLyIntent
import ly.img.android.pesdk.ui.utils.PermissionRequest

class MyEditorBuilder(activity: Activity) : ImgLyIntent(activity, MyEditorActivity::class.java) {

    override fun startActivityForResult(context: Activity, resultId: Int) =
        startActivityForResult(ResultDelegator(context), resultId, PermissionRequest.NEEDED_EDITOR_PERMISSIONS)

    override fun startActivityForResult(@Suppress("DEPRECATION") context: Fragment, resultId: Int) =
        startActivityForResult(ResultDelegator(context), resultId, PermissionRequest.NEEDED_EDITOR_PERMISSIONS)

    fun startActivityForResult(context: androidx.fragment.app.Fragment, resultId: Int) =
        startActivityForResult(ResultDelegator(context), resultId, PermissionRequest.NEEDED_EDITOR_PERMISSIONS)

    public override fun setSettingsList(settingsList: SettingsList): MyEditorBuilder {
        super.setSettingsList(settingsList)
        return this
    }

}