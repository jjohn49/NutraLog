package nutra.log.backend.requests

import nutra.log.backend.models.OpenFoodFact
import java.time.LocalDate
import java.util.Date

data class AddFootToDayRequest(
    val date: LocalDate,
    val food: OpenFoodFact,
    val servings: Double
)


