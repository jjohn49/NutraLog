package requests

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import response.LogInResponse
import response.RegisterResponse

data class RegisterUserRequest(
    val id: String,
    val email: String,
    val password: String
): GenericRequest{
    override suspend fun makeRequest(): RegisterResponse {
        val uri: String = "http://localhost:8080/auth/signup"
        val response = client.post(uri){
            setBody(this)
        }
        println(response)
        val ret = RegisterResponse(true,response.bodyAsText(),"Finished", this)
        return ret
    }
}

data class LogInRequest(
    val username:String,
    val password: String
): GenericRequest {
    override suspend fun makeRequest(): LogInResponse {
        val uri: String = "http://localhost:8080/auth/login"
        val response = client.post(uri){
            setBody(this)
        }
        println(response)
        val ret = LogInResponse(true,response.body(),"Logged In", this)
        return ret
    }
}