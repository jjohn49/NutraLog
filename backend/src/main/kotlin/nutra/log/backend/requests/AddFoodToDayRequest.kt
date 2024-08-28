package nutra.log.backend.requests

import nutra.log.backend.models.FoodServing
import java.time.LocalDate

data class AddFoodToDayRequest(
    val date: LocalDate,
    val foodServing: FoodServing
)