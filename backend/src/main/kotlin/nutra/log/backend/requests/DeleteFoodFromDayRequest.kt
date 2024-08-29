package nutra.log.backend.requests

import nutra.log.backend.models.FoodServing
import java.time.LocalDate

data class DeleteFoodFromDayRequest(
    val dayId: String,
    val foodServing: FoodServing
)
