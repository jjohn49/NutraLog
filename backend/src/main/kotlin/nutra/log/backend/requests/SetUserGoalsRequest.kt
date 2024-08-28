package nutra.log.backend.requests

import nutra.log.backend.models.UserGoal

data class SetUserGoalsRequest(
    val userGoal: UserGoal
)