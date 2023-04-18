package com.kuyaki.intelligpt.toolWindow

import com.intellij.ui.JBColor
import com.intellij.util.ui.JBUI
import com.kuyaki.intelligpt.GPTBundle
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.event.ActionEvent
import java.awt.event.KeyEvent
import javax.swing.AbstractAction
import javax.swing.JTextArea
import javax.swing.KeyStroke
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

class TrimmedTextArea(onEnterPressed: (String) -> Unit) : JTextArea() {
    private var placeholder = GPTBundle.message("inputTextArea.placeholder")
    private var showPlaceholder: Boolean = true

    init {
        lineWrap = true
        wrapStyleWord = true
        margin = JBUI.insets(5)

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, KeyEvent.SHIFT_DOWN_MASK), "newline")
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "send")
        actionMap.put("newline", object : AbstractAction() {
            override fun actionPerformed(e: ActionEvent) {
                insert("\n", caretPosition)
            }
        })
        actionMap.put("send", object : AbstractAction() {
            override fun actionPerformed(e: ActionEvent) {
                onEnterPressed(text.trim())
                text = ""
            }
        })

        document.addDocumentListener(object : DocumentListener {
            override fun insertUpdate(e: DocumentEvent) {
                showPlaceholder = text.isEmpty()
                repaint()
            }

            override fun removeUpdate(e: DocumentEvent) {
                showPlaceholder = text.isEmpty()
                repaint()
            }

            override fun changedUpdate(e: DocumentEvent) {
                showPlaceholder = text.isEmpty()
                repaint()
            }
        })
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        if (showPlaceholder) {
            val g2d = g.create() as Graphics2D
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
            g2d.color = JBColor.GRAY
            g2d.drawString(placeholder, JBUI.scale(5), JBUI.scale(18))
            g2d.dispose()
        }
    }
}
