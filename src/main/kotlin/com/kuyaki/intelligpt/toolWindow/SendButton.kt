package com.kuyaki.intelligpt.toolWindow

import com.intellij.openapi.util.IconLoader
import com.intellij.ui.JBColor
import com.kuyaki.intelligpt.GPTBundle
import com.kuyaki.intelligpt.utils.isDarkTheme
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.Icon
import javax.swing.JButton

class SendButton(private val onSend: () -> Unit) : JButton() {
    init {
        val isDarkTheme = isDarkTheme()
        val whiteIcon: Icon = IconLoader.getIcon("/icons/send/send_white.png", javaClass)
        val blackIcon: Icon = IconLoader.getIcon("/icons/send/send_black.png", javaClass)

        icon = if (isDarkTheme) whiteIcon else blackIcon
        background = if (isDarkTheme) JBColor.DARK_GRAY else JBColor.LIGHT_GRAY

        isContentAreaFilled = false
        isFocusPainted = false

        toolTipText =  GPTBundle.message("sendButton.tooltip")

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
