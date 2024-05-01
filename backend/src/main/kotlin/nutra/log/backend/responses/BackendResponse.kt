package nutra.log.backend.responses

data class BackendResponse(
    val success: Boolean,
    val message: String
)

data class SuccessfulLoginResponse(
    val token: String
)