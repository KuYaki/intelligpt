package com.kuyaki.intelligpt.toolWindow

import com.intellij.credentialStore.CredentialAttributes
import com.intellij.credentialStore.Credentials
import com.intellij.ide.passwordSafe.PasswordSafe
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
        val passwordSafe = PasswordSafe.instance
        val credentialAttributes = createKey()
        val credentials = Credentials("", apiKey)
        passwordSafe.set(credentialAttributes, credentials)
    }

    private fun loadApiKey(): String? {
        val passwordSafe = PasswordSafe.instance
        val credentialAttributes = createKey()
        val credentials = passwordSafe.get(credentialAttributes)
        return credentials?.getPasswordAsString()
    }

    private fun createKey(): CredentialAttributes {
        return CredentialAttributes("IntelliGPT-API-Key")
    }
}
