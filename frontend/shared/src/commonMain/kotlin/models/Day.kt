package models

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Day(
    val id: String,
    val userId: String,
    val date: LocalDate,
    val foodsEaten: ArrayList<FoodServing> = arrayListOf()
)
