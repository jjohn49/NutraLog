package utils

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import requests.LogInRequest
import response.LogInBody
import response.LogInResponse

class RequestUtil {

    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json{
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

     suspend fun sendLoginRequest(req: LogInRequest): LogInResponse{
        val uri: String = "http://localhost:8080/auth/login"

         println(req)

        val response: LogInResponse = client.post(uri){
            contentType(ContentType.Application.Json)
            setBody(req)
        }.body()

         println(response)

        return response
    }
}