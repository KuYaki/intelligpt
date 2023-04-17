package com.kuyaki.intelligpt.utils

import com.intellij.ide.ui.laf.darcula.DarculaLaf
import javax.swing.UIManager

fun isDarkTheme(): Boolean {
    val laf = UIManager.getLookAndFeel()

    return when (laf.javaClass.name) {
        DarculaLaf::class.java.name -> true
        "com.intellij.ide.ui.laf.intellij.IntelliJLaf" -> false
        else -> laf.name.contains("dark", ignoreCase = true) || laf.name.contains("darcula", ignoreCase = true)
    }
}
