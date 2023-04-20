package com.kuyaki.intelligpt.toolWindow

import com.intellij.openapi.util.IconLoader
import com.intellij.ui.JBColor
import com.kuyaki.intelligpt.GPTBundle
import com.kuyaki.intelligpt.utils.isDarkTheme
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.Icon
import javax.swing.JToggleButton

class VoiceToggleButton(
    private val onVoiceStart: () -> Unit,
    private val onVoiceEnd: () -> Unit
) : JToggleButton() {

    init {
        val whiteIcon: Icon = IconLoader.getIcon("/icons/mic/mic_white.png", javaClass)
        val blackIcon: Icon = IconLoader.getIcon("/icons/mic/mic_black.png", javaClass)
        icon = if (isDarkTheme()) whiteIcon else blackIcon
        background = if (isDarkTheme()) JBColor.DARK_GRAY else JBColor.LIGHT_GRAY
        isContentAreaFilled = false

        isFocusable = false

        toolTipText = GPTBundle.message("voiceToggleButton.tooltip")

        addMouseListener(object : MouseAdapter() {
            override fun mouseEntered(e: MouseEvent?) {
                if (!isSelected) {
                    isContentAreaFilled = true
                }
            }

            override fun mouseExited(e: MouseEvent?) {
                if (!isSelected) {
                    isContentAreaFilled = false
                }
            }
        })

        addItemListener {
            if (isSelected) {
                onVoiceStart()
                background = if (isDarkTheme()) JBColor.RED else JBColor.PINK
                isContentAreaFilled = true
            } else {
                onVoiceEnd()
                background = if (isDarkTheme()) JBColor.DARK_GRAY else JBColor.LIGHT_GRAY
                isContentAreaFilled = true
            }
        }
    }
}
