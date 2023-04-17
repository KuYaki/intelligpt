package com.kuyaki.intelligpt.toolWindow

import com.intellij.util.ui.JBUI
import java.awt.event.ActionEvent
import java.awt.event.KeyEvent
import javax.swing.AbstractAction
import javax.swing.JTextArea
import javax.swing.KeyStroke

class TrimmedTextArea(onEnterPressed: (String) -> Unit) : JTextArea() {
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
    }
}
