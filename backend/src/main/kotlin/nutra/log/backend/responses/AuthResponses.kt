package nutra.log.backend.responses

import kotlinx.serialization.Serializable
import nutra.log.backend.models.User
import nutra.log.backend.requests.LogInRequest
import nutra.log.backend.requests.RegisterUserRequest

@Serializable
data class RegisterResponse(
    override val success: Boolean,
    override val body: SuccessfulLoginResponse,
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
    //val user: User?
)