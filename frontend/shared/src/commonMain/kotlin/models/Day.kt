package models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Day(
    @PrimaryKey(autoGenerate = false) val id: String = "",
    val userId: String = "",
    val date: LocalDate = Clock.System.now().toLocalDateTime(kotlinx.datetime.TimeZone.currentSystemDefault()).date,
    var foodsEaten: List<FoodServing> = mutableListOf(),
    var userNutrients: UserNutrients = UserNutrients()
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

    fun addFoodServingToDay(foodServing: FoodServing){
        foodsEaten += foodServing
    }

    fun dateToString() : String {
        return this.toString()
    }

    companion object{
        fun getEmptyDay(): Day {
            return Day("")
        }
    }
}

@Serializable
data class DayId(
    val timestamp: Int,
    val date: String
)
