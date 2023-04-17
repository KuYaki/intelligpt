package com.kuyaki.intelligpt.toolWindow

import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

abstract class SimpleDocumentListener : DocumentListener {
    abstract fun update()

    override fun insertUpdate(e: DocumentEvent) {
        update()
    }

    override fun removeUpdate(e: DocumentEvent) {
        update()
    }

    override fun changedUpdate(e: DocumentEvent) {
        update()
    }
}
