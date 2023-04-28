package com.kuyaki.intelligpt.api

import kotlinx.serialization.Serializable

@Serializable
data class OpenAIError(val error: OpenAIErrorMessage)

@Serializable
data class OpenAIErrorMessage(val message: String)
