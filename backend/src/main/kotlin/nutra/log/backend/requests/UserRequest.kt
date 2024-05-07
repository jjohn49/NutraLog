package nutra.log.backend.requests

import nutra.log.backend.models.UserGoal

data class UserRequest (
    val token: String
)

data class SetUserGoalsRequest(
    val userGoal: UserGoal
)