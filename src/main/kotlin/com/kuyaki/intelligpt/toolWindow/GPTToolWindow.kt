package com.kuyaki.intelligpt.toolWindow

import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTextArea
import javax.swing.JToggleButton
import java.awt.BorderLayout

class GPTToolWindow {
    val mainPanel: JPanel = JPanel(BorderLayout())

    init {
        val textArea = JTextArea().apply { isEditable = false }
        val inputField = GPTInputField { message ->
            // Здесь вы можете обработать отправленное сообщение
            println("Message sent: $message")
        }
        val voiceToggleButton = JToggleButton("Voice Input").apply { isFocusable = false }

        mainPanel.add(JScrollPane(textArea), BorderLayout.CENTER)
        mainPanel.add(inputField, BorderLayout.SOUTH)
        mainPanel.add(voiceToggleButton, BorderLayout.NORTH)
    }
}
