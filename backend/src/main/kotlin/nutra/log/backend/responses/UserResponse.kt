package nutra.log.backend.responses

import nutra.log.backend.models.User

data class UserResponse(
    val success:Boolean,
    val user: User?,
    val message: String?
)
