package requests

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

data class RegisterUserRequest(
    val id: String,
    val email: String,
    val password: String
): GenericRequest{
    override suspend fun makeRequest(): Any {
        val uri: String = "http://localhost:8080/auth/signup"
        val response = client.post(uri){
            setBody(this)
        }
        return response
    }
}

data class LogInRequest(
    val username:String,
    val password: String
): GenericRequest {
    override suspend fun makeRequest(): Any {
        val uri: String = "http://localhost:8080/auth/login"
        val response = client.post(uri){
            setBody(this)
        }
        return response
    }
}