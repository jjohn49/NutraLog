package nutra.log.backend.responses

import kotlinx.serialization.Serializable

@Serializable
data class BackendResponse(
    val success: Boolean,
    val message: String
)

@Serializable
data class SuccessfulLoginResponse(
    val token: String
)