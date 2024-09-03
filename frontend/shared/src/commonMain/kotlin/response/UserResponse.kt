package response

import kotlinx.serialization.Serializable
import models.UserGoal
import models.UserKMM

@Serializable
data class UserResponse(
    val success:Boolean,
    val user: UserKMM? = null,
    val message: String? = null
)

@Serializable
data class SetUserGoalResponse(
    val success: Boolean,
    val userGoal: UserGoal? = null,
    val message: String? = null
)