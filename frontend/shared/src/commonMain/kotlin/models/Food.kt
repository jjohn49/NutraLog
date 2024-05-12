package models

import kotlinx.serialization.Serializable

@Serializable
data class Food(
    val id: String,
    val name: String,
    val servingSize: String,
    val calories: Double?,
    val proteinGrams:Double?,
    val carbGrams:Double?,
    val fatGrams:Double?,
    val brand: String = ""

)
