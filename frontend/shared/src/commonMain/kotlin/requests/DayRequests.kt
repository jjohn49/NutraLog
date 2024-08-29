package requests

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import models.FoodServing

@Serializable
data class CreateDayRequest(
    val date: LocalDate
)

@Serializable
data class AddFoodToDayRequest(
    val date: LocalDate,
    val foodServing: FoodServing
)

@Serializable
data class DeleteFoodFromDayRequest(
    val dayId: String,
    val foodServing: FoodServing,
)