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
){
    fun toUserNutrients(): UserNutrients{
        if(calories == null || proteinGrams == null || carbGrams == null || fatGrams == null){
            throw Error("Failed to convert food: ${name} to UserNutrient.  One of the necessary macros was null for food.")
        }

        return UserNutrients(calories, proteinGrams, carbGrams, fatGrams)
    }
}
