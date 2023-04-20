package com.kuyaki.intelligpt.toolWindow

import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPasswordField
import com.intellij.util.ui.JBUI
import com.kuyaki.intelligpt.GPTBundle
import com.kuyaki.intelligpt.api.GPTApi
import java.awt.BorderLayout
import java.awt.FlowLayout
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Image
import javax.imageio.ImageIO
import javax.swing.BoxLayout
import javax.swing.ImageIcon
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

        val formPanel = JPanel(GridBagLayout()).apply {
            val constraints = GridBagConstraints().apply {
                anchor = GridBagConstraints.CENTER
                fill = GridBagConstraints.HORIZONTAL
                weightx = 0.0
                gridx = 0
                gridy = 0
            }
            add(apiKeyLabel, constraints)

            constraints.gridx = 1
            constraints.weightx = 1.0
            add(apiKeyField, constraints)
            add(loginButton)
        }

        val titleLabel = JBLabel(GPTBundle.message("authentication.title")).apply {
            font = font.deriveFont(24f)
        }

        val logoImage = ImageIO.read(AuthenticationPanel::class.java.getResource("/images/logo.png"))
        val fixedSizeLogo = logoImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH)
        val logoLabel = JBLabel(ImageIcon(fixedSizeLogo))

        val titleAndLogoPanel = JPanel().apply {
            layout = BoxLayout(this, BoxLayout.PAGE_AXIS)
            add(JPanel(FlowLayout(FlowLayout.CENTER)).apply { add(logoLabel) })
            add(JPanel(FlowLayout(FlowLayout.CENTER)).apply { add(titleLabel) })
        }

        val copyrightLabel = JBLabel(GPTBundle.message("authentication.footer")).apply {
            font = font.deriveFont(12f)
        }

        val copyrightPanel = JPanel(FlowLayout(FlowLayout.CENTER)).apply {
            add(copyrightLabel)
        }

        val mainPanel = JPanel(BorderLayout())
        mainPanel.add(formPanel, BorderLayout.CENTER)

        layout = BorderLayout()
        add(titleAndLogoPanel, BorderLayout.NORTH)
        add(mainPanel, BorderLayout.CENTER)
        add(copyrightPanel, BorderLayout.SOUTH)
        border = JBUI.Borders.empty(16)
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
