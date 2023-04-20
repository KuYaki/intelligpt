package com.kuyaki.intelligpt.toolWindow

import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPasswordField
import com.intellij.util.ui.FormBuilder
import com.kuyaki.intelligpt.GPTBundle
import com.kuyaki.intelligpt.api.GPTApi
import javax.swing.JButton
import javax.swing.JOptionPane
import javax.swing.JPanel

class AuthenticationPanel(private val onAuthenticated: (String) -> Unit) : JPanel() {

    private val apiKeyField = JBPasswordField()

    init {
        val apiKeyLabel = JBLabel(GPTBundle.message("authentication.apiKeyLabel"))

        val loginButton = JButton(GPTBundle.message("authentication.loginButton"))
        loginButton.addActionListener {
            val apiKey = String(apiKeyField.password)
            GPTApi.validateApiKey(
                apiKey,
                onSuccess = { onAuthenticated(apiKey) },
                onError = { errorMessage -> showAuthenticationError(errorMessage) }
            )
        }

        val formBuilder = FormBuilder.createFormBuilder()
            .addLabeledComponent(apiKeyLabel, apiKeyField)
            .addComponent(loginButton)

        add(formBuilder.panel)
    }

    private fun showAuthenticationError(errorMessage: String) {
        JOptionPane.showMessageDialog(
            this,
            errorMessage,
            GPTBundle.message("authentication.error.title"),
            JOptionPane.ERROR_MESSAGE
        )
    }
}
