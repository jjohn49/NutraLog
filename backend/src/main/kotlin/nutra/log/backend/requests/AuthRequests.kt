package nutra.log.backend.requests

data class RegisterUserRequest(
    val id: String,
    val email: String,
    val password: String
)

data class LogInRequest(
    val username:String,
    val password: String
)