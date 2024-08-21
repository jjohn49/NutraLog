package models

import kotlinx.serialization.Serializable

@Serializable
data class UserNutrients(
    var calories: Double = 0.0,
    var proteinGrams: Double = 0.0,
    var carbGrams: Double = 0.0,
    var fatGrams: Double = 0.0
){
    fun multiplyByServings(servings: Double): UserNutrients{
        return UserNutrients(calories * servings, proteinGrams * servings, carbGrams * servings, fatGrams * servings)
    }
}
