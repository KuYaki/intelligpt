package com.kuyaki.intelligpt.toolWindow

import com.intellij.ui.components.JBScrollPane
import java.awt.BorderLayout
import java.awt.CardLayout
import java.awt.Dimension
import javax.swing.JPanel
import javax.swing.UIManager

class GPTInputField(private val onSend: (String) -> Unit) : JPanel(BorderLayout()) {
    private val inputArea: TrimmedTextArea = TrimmedTextArea { message ->
        onSend(message)
    }
    private val sendButton: SendButton = SendButton {
        onSend(inputArea.text.trim())
        inputArea.text = ""
    }
    private val voiceToggleButton: VoiceToggleButton = VoiceToggleButton({}) {}

    init {
        background = UIManager.getColor("Panel.background")

        inputArea.isOpaque = false
        val scrollPane = JBScrollPane(inputArea)
        scrollPane.preferredSize = Dimension(0, 50)

        add(scrollPane, BorderLayout.CENTER)

        val cardLayout = CardLayout()
        val buttonsPanel = JPanel(cardLayout)
        buttonsPanel.isOpaque = false
        buttonsPanel.add(sendButton, "SEND_BUTTON")
        buttonsPanel.add(voiceToggleButton, "VOICE_TOGGLE_BUTTON")
        add(buttonsPanel, BorderLayout.EAST)

        updateButtonVisibility(cardLayout, buttonsPanel)

        inputArea.document.addDocumentListener(object : SimpleDocumentListener() {
            override fun update() {
                updateButtonVisibility(cardLayout, buttonsPanel)
            }
        })
    }

    private fun updateButtonVisibility(cardLayout: CardLayout, buttonsPanel: JPanel) {
        if (inputArea.text.trim().isNotEmpty()) {
            cardLayout.show(buttonsPanel, "SEND_BUTTON")
        } else {
            cardLayout.show(buttonsPanel, "VOICE_TOGGLE_BUTTON")
        }
    }
}
