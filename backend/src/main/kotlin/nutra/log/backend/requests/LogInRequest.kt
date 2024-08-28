package nutra.log.backend.requests

import kotlinx.serialization.Serializable

@Serializable
data class LogInRequest(
    val username:String,
    val password: String
)