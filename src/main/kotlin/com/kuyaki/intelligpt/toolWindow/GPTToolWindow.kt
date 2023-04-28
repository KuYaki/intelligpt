package com.kuyaki.intelligpt.toolWindow

import com.kuyaki.intelligpt.communicator.GPTCommunicator
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTextArea
import java.awt.BorderLayout

class GPTToolWindow {
    val mainPanel: JPanel = JPanel(BorderLayout())

    init {
        val textArea = JTextArea().apply { isEditable = false }
        val inputField = GPTInputField { message ->
            textArea.append("User: $message\n")
            GPTCommunicator.userInput(
                input = message,
                onUpdateMessage = { response ->
                    textArea.append("GPT-4: $response\n")
                },
                onError = { error ->
                    textArea.append("Error: $error\n")
                }
            )
        }

        mainPanel.add(JScrollPane(textArea), BorderLayout.CENTER)
        mainPanel.add(inputField, BorderLayout.SOUTH)
    }
}
