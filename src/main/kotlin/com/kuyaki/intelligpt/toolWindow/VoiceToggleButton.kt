package com.kuyaki.intelligpt.toolWindow

import com.intellij.ui.JBColor
import com.kuyaki.intelligpt.utils.isDarkTheme
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.Icon
import javax.swing.ImageIcon
import javax.swing.JToggleButton

class VoiceToggleButton(
    private val onVoiceStart: () -> Unit,
    private val onVoiceEnd: () -> Unit
) : JToggleButton() {

    init {
        val whiteIcon: Icon = ImageIcon(javaClass.getResource("/icons/mic_white.png"))
        val blackIcon: Icon = ImageIcon(javaClass.getResource("/icons/mic_black.png"))
        icon = if (isDarkTheme()) whiteIcon else blackIcon
        background = if (isDarkTheme()) JBColor.DARK_GRAY else JBColor.LIGHT_GRAY
        isContentAreaFilled = false

        isFocusable = false

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
