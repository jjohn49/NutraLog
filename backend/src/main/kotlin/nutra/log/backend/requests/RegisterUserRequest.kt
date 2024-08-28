package nutra.log.backend.requests

import kotlinx.serialization.Serializable

@Serializable
data class RegisterUserRequest(
    val id: String,
    val email: String,
    val password: String
)

