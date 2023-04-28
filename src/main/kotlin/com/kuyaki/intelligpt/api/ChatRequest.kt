package com.kuyaki.intelligpt.api

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class ChatRequest(
    val model: String,
    val messages: List<Message>,
    @SerialName("max_tokens")
    val maxTokens: Int,
    val stop: List<String>? = null,
    val temperature: Double
)
