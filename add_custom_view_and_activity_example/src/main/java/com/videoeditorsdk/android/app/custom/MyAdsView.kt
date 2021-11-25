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
import ly.img.android.pesdk.backend.model.state.manager.stateHandlerResolve
import ly.img.android.pesdk.ui.model.state.UiStateMenu
import ly.img.android.pesdk.ui.panels.MenuToolPanel

class MyAdsView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ImgLyUIRelativeContainer(context, attrs), View.OnClickListener {

    /*
     * This is an example of extending the SDK by creating a view extending one of the ImgLyUI**** views,
     * like `ImgLyUIFrameContainer`, `ImgLyUILinearContainer`, `ImgLyUIRelativeContainer`.
     *
     * Using these views can have two advantages.
     *   1. The view will automatically subscribe to events. Simply use the @OnEvent annotation to listen to any.
     *   2. Use `val name: ModelClass by stateHandlerResolve()` to resolve any `ImglyState` or `ImglySettings` class.
     *
     * See how you could implement the same by extending from any other system view by looking at `MyRawAdsView`.
     */

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