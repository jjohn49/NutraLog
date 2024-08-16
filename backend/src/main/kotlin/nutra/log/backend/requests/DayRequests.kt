package nutra.log.backend.requests

import nutra.log.backend.models.OpenFoodFact
import java.time.LocalDate

data class CreateDayRequest(
    val date: LocalDate
)
data class AddFootToDayRequest(
    val date: LocalDate,
    val food: OpenFoodFact,
    val servings: Double
)


