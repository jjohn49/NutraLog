package nutra.log.backend.requests

import nutra.log.backend.models.OpenFoodFact

data class DayRequest(
    val userId: String
)

data class AddFootToDayRequest(
    val userId: String,
    val dayId: String,
    val food: OpenFoodFact,
    val servings: Double
)

