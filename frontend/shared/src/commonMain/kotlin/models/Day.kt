package models

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Day(
    val id: DayId,
    val userId: String,
    val foodsEaten: ArrayList<FoodServing> = arrayListOf()
){
    fun toUserNutrients(): UserNutrients{
        var calories: Double = 0.0
        var pro: Double = 0.0
        var carb: Double = 0.0
        var fat: Double = 0.0
        foodsEaten.forEach {foodServing ->
            try {
                val foodServingNutrients = foodServing.toUserNutrients()

                calories += foodServingNutrients.calories
                pro += foodServingNutrients.proteinGrams
                carb += foodServingNutrients.carbGrams
                fat += foodServingNutrients.fatGrams
            }catch (e: Exception){
                println("Failed to create a UserNutrient for a food.  Skipping to next")
            }
        }

        return UserNutrients(calories, pro, carb, fat)
    }
}

@Serializable
data class DayId(
    val timestamp: Int,
    val date: String
)
