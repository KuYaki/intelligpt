package com.kuyaki.intelligpt.api

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class ChatResponse(
    val id: String,
    @SerialName("object")
    val responseObject: String,
    @SerialName("created")
    val creationTimestamp: Long,
    val model: String,
    val usage: ChatUsage,
    val choices: List<ChatChoice>
)

@Serializable
data class ChatUsage(
    @SerialName("prompt_tokens")
    val promptTokens: Int,
    @SerialName("completion_tokens")
    val completionTokens: Int,
    @SerialName("total_tokens")
    val totalTokens: Int
)

@Serializable
data class ChatChoice(
    val message: Message,
    val index: Int,
    @SerialName("finish_reason")
    val finishReason: String
)