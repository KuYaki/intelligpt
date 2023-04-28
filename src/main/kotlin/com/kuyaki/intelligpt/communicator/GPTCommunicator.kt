package com.kuyaki.intelligpt.communicator

import com.kuyaki.intelligpt.api.GPTApi
import com.kuyaki.intelligpt.api.Message

object GPTCommunicator {
    private const val CONTEXT_SIZE = 20
    private val dialog = mutableListOf<Message>()

    private fun getChatMessages(): List<Message> {
        return if (dialog.size > CONTEXT_SIZE) {
            dialog.subList(dialog.size - CONTEXT_SIZE, dialog.size)
        } else {
            dialog
        }
    }

    fun userInput(
        input: String,
        onUpdateMessage: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        dialog.add(Message("user", input))
        val chatMessages = getChatMessages()

        GPTApi.sendChatMessages(chatMessages, { response ->
            dialog.add(Message("assistant", response))
            onUpdateMessage(response)
        }, onError)
    }
}
