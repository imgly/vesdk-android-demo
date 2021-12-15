package com.videoeditorsdk.android.app.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.annotation.MainThread
import com.videoeditorsdk.android.app.R
import ly.img.android.pesdk.annotations.OnEvent
import ly.img.android.pesdk.backend.model.state.manager.StateHandler
import ly.img.android.pesdk.backend.model.state.manager.StateHandler.StateHandlerNotFoundException
import ly.img.android.pesdk.ui.model.state.UiStateMenu
import ly.img.android.pesdk.ui.panels.MenuToolPanel

class MyCustomBackButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : TextView(context, attrs), View.OnClickListener {

    private val cancelText: Int = R.string.pesdk_editor_cancel
    private var stateHandler = if (!isInEditMode) {
        StateHandler.findInViewContext(context)
    } else StateHandler(context)

    val uiStateMenu = stateHandler[UiStateMenu::class]

    init {
        setText(cancelText)
        setOnClickListener(this)
    }

    @MainThread
    @OnEvent(value = [UiStateMenu.Event.ENTER_TOOL, UiStateMenu.Event.LEAVE_TOOL, UiStateMenu.Event.LEAVE_AND_REVERT_TOOL], triggerDelay = 30)
    protected fun onToolChanged() {
        val currentToolData = uiStateMenu.currentStackData
        val currentTool = currentToolData.toolPanel
        if (currentTool.isAttached) {
            visibility = if (currentTool.isCancelable) VISIBLE else GONE

            text = resources.getText(
                if (currentToolData.panelData.id == MenuToolPanel.TOOL_ID) {
                    R.string.close_button
                } else {
                    R.string.cancal_button
                }
            )
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        stateHandler.registerSettingsEventListener(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stateHandler.unregisterSettingsEventListener(this)
    }

    override fun onClick(v: View) {
        if (UiStateMenu.MAIN_TOOL_ID == uiStateMenu.currentPanelData.id) {
            uiStateMenu.notifyCloseClicked()
        } else {
            uiStateMenu.notifyCancelClicked()
        }
    }
}