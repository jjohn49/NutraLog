package requests

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import response.LogInResponse
import response.RegisterResponse

@Serializable
data class RegisterUserRequest(
    val id: String,
    val email: String,
    val password: String
)

@Serializable
data class LogInRequest(
    val username:String,
    val password: String
)
