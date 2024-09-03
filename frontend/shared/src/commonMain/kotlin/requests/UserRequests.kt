package requests

import kotlinx.serialization.Serializable
import models.UserGoal

@Serializable
data class SetUserGoalsRequest(
    var userGoal: UserGoal
)