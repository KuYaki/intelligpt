package com.kuyaki.intelligpt.toolWindow

import com.intellij.ui.JBColor
import com.kuyaki.intelligpt.utils.isDarkTheme
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.BorderFactory
import javax.swing.Icon
import javax.swing.ImageIcon
import javax.swing.JButton

class SendButton(private val onSend: () -> Unit) : JButton() {
    init {
        val isDarkTheme = isDarkTheme()
        val whiteIcon: Icon = ImageIcon(javaClass.getResource("/icons/send_white.png"))
        val blackIcon: Icon = ImageIcon(javaClass.getResource("/icons/send_black.png"))

        icon = if (isDarkTheme) whiteIcon else blackIcon
        background = if (isDarkTheme) JBColor.DARK_GRAY else JBColor.LIGHT_GRAY

        isContentAreaFilled = false
        isFocusPainted = false

        addMouseListener(object : MouseAdapter() {
            override fun mouseEntered(e: MouseEvent?) {
                isContentAreaFilled = true
            }

            override fun mouseExited(e: MouseEvent?) {
                isContentAreaFilled = false
            }
        })

        addActionListener { onSend() }
    }
}
