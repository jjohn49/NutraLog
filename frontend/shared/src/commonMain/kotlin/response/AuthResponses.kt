package response

import requests.LogInRequest
import requests.RegisterUserRequest

data class RegisterResponse(
    override val success: Boolean,
    override val body: Any,
    override val message: String,
    override val request: RegisterUserRequest
):GenericResponse<RegisterUserRequest>

data class LogInResponse(
    override val success: Boolean,
    override val body: Any,
    override val message: String,
    override val request: LogInRequest,
): GenericResponse<LogInRequest>

