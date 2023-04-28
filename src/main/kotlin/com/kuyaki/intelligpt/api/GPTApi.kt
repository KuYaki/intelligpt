package com.kuyaki.intelligpt.api

import com.kuyaki.intelligpt.GPTBundle
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.swing.Swing
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object GPTApi {
    private const val apiUrl = "https://api.openai.com/v1"
    private var apiKey: String? = null
    private val jsonParser = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }
    private val httpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(jsonParser)
        }
    }

    fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    fun validateApiKey(apiKey: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch(Dispatchers.Swing) {
            try {
                val isValid = checkApiKeyValidity(apiKey)
                if (isValid) {
                    onSuccess()
                } else {
                    onError(GPTBundle.message("authentication.error.invalidApiKey"))
                }
            } catch (e: Exception) {
                onError(GPTBundle.message("authentication.error.validationError", e.localizedMessage))
            }
        }
    }

    fun sendChatMessages(
        input: List<Message>,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch(Dispatchers.Swing) {
            try {
                val response = sendChatRequest(input)
                onSuccess(response)
            } catch (e: Exception) {
                onError(GPTBundle.message("api.sendMessageError", e.localizedMessage))
            }
        }
    }

    private suspend fun sendChatRequest(input: List<Message>): String {
        val requestBody = ChatRequest(
            model = "gpt-3.5-turbo",
            messages = input,
            maxTokens = 150,
            temperature = 0.7
        )

        val response: HttpResponse = httpClient.post("$apiUrl/chat/completions") {
            header("Authorization", "Bearer $apiKey")
            contentType(ContentType.Application.Json)
            setBody(Json.encodeToString(requestBody))
        }

        val responseBody = response.bodyAsText()

        return if (response.status.isSuccess()) {
            val responseObject = jsonParser.decodeFromString<ChatResponse>(responseBody)
            responseObject.choices.first().message.content.trim()
        } else {
            val errorObject = jsonParser.decodeFromString<OpenAIError>(responseBody)
            throw Exception(errorObject.error.message)
        }
    }

    private suspend fun checkApiKeyValidity(apiKey: String): Boolean {
        val response = httpClient.get("$apiUrl/models") {
            header("Authorization", "Bearer $apiKey")
        }
        return response.status == HttpStatusCode.OK
    }
}
