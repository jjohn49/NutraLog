package models

import kotlinx.serialization.Serializable

@Serializable
data class FoodServing(
    var numberOfServings: Double,
    var food: Food
){
    fun toUserNutrients(): UserNutrients{
        return food.toUserNutrients().multiplyByServings(this.numberOfServings)
    }
}
