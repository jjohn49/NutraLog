package nutra.log.backend.responses

import kotlinx.serialization.Serializable

@Serializable
data class SuccessfulLoginResponse(
    val token: String
)