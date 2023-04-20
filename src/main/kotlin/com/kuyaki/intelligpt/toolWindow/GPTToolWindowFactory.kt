package com.kuyaki.intelligpt.toolWindow

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory
import com.kuyaki.intelligpt.api.GPTApi

class GPTToolWindowFactory : ToolWindowFactory {
    private val contentFactory = ContentFactory.SERVICE.getInstance()

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val savedApiKey = loadApiKey()

        if (savedApiKey != null) {
            GPTApi.validateApiKey(
                savedApiKey,
                onSuccess = {
                    GPTApi.setApiKey(savedApiKey)
                    showGptToolWindow(toolWindow)
                },
                onError = { showAuthenticationPanel(toolWindow) }
            )
        } else {
            showAuthenticationPanel(toolWindow)
        }
    }

    private fun showAuthenticationPanel(toolWindow: ToolWindow) {
        val authenticationPanel = AuthenticationPanel { apiKey ->
            GPTApi.setApiKey(apiKey)
            saveApiKey(apiKey)
            showGptToolWindow(toolWindow)
        }

        val content = contentFactory.createContent(authenticationPanel, "", false)
        toolWindow.contentManager.addContent(content)
    }

    private fun showGptToolWindow(toolWindow: ToolWindow) {
        val gptToolWindow = GPTToolWindow()

        val content = contentFactory.createContent(gptToolWindow.mainPanel, "", false)

        toolWindow.contentManager.removeAllContents(true)
        toolWindow.contentManager.addContent(content)
    }
    private fun saveApiKey(apiKey: String) {
        val properties = PropertiesComponent.getInstance()
        properties.setValue("intelligpt.apiKey", apiKey)
    }
    private fun loadApiKey(): String? {
        val properties = PropertiesComponent.getInstance()
        return properties.getValue("intelligpt.apiKey")
    }
}
