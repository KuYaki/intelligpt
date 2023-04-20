package com.kuyaki.intelligpt.api

import com.kuyaki.intelligpt.GPTBundle
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.swing.Swing
import kotlinx.serialization.json.Json

object GPTApi {
    private const val apiUrl = "https://api.openai.com/v1/"
    private var apiKey: String? = null
    private val httpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                ignoreUnknownKeys = true
            })
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

    private suspend fun checkApiKeyValidity(apiKey: String): Boolean {
        val response = httpClient.get("$apiUrl/models") {
            header("Authorization", "Bearer $apiKey")
        }
        return response.status == HttpStatusCode.OK
    }
}
