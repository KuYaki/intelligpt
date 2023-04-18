package com.kuyaki.intelligpt.toolWindow

import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTextArea
import java.awt.BorderLayout

class GPTToolWindow {
    val mainPanel: JPanel = JPanel(BorderLayout())

    init {
        val textArea = JTextArea().apply { isEditable = false }
        val inputField = GPTInputField { message ->
            // Here you can process the message
            println("Message sent: $message")
        }

        mainPanel.add(JScrollPane(textArea), BorderLayout.CENTER)
        mainPanel.add(inputField, BorderLayout.SOUTH)
    }
}
