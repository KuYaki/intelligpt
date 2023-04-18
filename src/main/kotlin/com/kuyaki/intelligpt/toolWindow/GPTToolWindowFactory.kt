package com.kuyaki.intelligpt.toolWindow

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory

class GPTToolWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val gptToolWindow = GPTToolWindow()

        val contentFactory = ContentFactory.SERVICE.getInstance()
        val content = contentFactory.createContent(gptToolWindow.mainPanel, "", false)
        toolWindow.contentManager.addContent(content)
    }
}
