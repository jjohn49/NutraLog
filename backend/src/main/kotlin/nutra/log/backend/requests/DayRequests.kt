package nutra.log.backend.requests

import nutra.log.backend.models.FoodServingKMM
import java.time.LocalDate

data class CreateDayRequest(
    val date: LocalDate
)
data class AddFoodToDayRequest(
    val date: LocalDate,
    val foodServing: FoodServingKMM
)


