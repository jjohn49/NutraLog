package response

import kotlinx.serialization.Serializable
import models.UserKMM

@Serializable
data class UserResponse(
    val success:Boolean,
    val user: UserKMM? = null,
    val message: String? = null
)