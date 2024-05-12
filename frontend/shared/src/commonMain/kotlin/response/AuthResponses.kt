package response

import io.ktor.http.content.NullBody
import kotlinx.serialization.Serializable
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
    override val body: SuccessfulLoginResponse,
    override val message: String,
    override val request: LogInRequest,
): GenericResponse<LogInRequest>

@Serializable
data class SuccessfulLoginResponse(
    val token: String
)

