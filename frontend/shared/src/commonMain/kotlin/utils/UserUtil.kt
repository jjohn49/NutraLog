package utils

import frontend.BuildKonfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.auth.parseAuthorizationHeader
import io.ktor.http.contentType
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import models.UserKMM
import response.LogInResponse
import response.UserResponse

class UserUtil {

    private val backend_url = BuildKonfig.BACKEND_URL

    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getUser(token: String): UserResponse{
        val uri: String = "${backend_url}/user/get"

        val response: HttpResponse = client.get(uri){
            header(HttpHeaders.Authorization, token)
        }

        return if (response.status.value in 200..299){
            UserResponse(true,response.body(),"Got User Details")
        }else{
            UserResponse(false,null,"Failed to get User Details")
        }
    }
}