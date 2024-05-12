package models

import kotlinx.serialization.Serializable

@Serializable
data class FoodServing(
    val numberOfServings: Double,
    val food: Food
)
