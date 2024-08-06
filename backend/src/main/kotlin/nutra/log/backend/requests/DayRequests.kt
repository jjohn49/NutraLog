package nutra.log.backend.requests

import nutra.log.backend.models.OpenFoodFact
import java.util.Date

data class AddFootToDayRequest(
    val date: Date,
    val food: OpenFoodFact,
    val servings: Double
)

