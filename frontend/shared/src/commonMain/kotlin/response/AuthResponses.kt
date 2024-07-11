package response

import io.ktor.http.content.NullBody
import kotlinx.serialization.Serializable
import models.UserKMM
import requests.LogInRequest
import requests.RegisterUserRequest

@Serializable
data class RegisterResponse(
    override val success: Boolean,
    override val body: String?,
    override val message: String,
    override val request: RegisterUserRequest
):GenericResponse<RegisterUserRequest>

@Serializable
data class LogInResponse(
    override val success: Boolean,
    override val body: LogInBody,
    override val message: String,
    override val request: LogInRequest,
): GenericResponse<LogInRequest>

@Serializable
data class LogInBody(
    val token: String,
    //val user: UserKMM? = null,
)

