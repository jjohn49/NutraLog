package Database

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDate
import kotlinx.serialization.json.Json
import models.FoodServing
import models.UserGoal
import models.UserNutrients

class TypeConverter {

    @TypeConverter
    fun fromUserGoal(goal: UserGoal): String {
        return Json.encodeToString(UserGoal.serializer(),goal)
    }

    @TypeConverter
    fun toUserGoal(value: String): UserGoal {
        return Json.decodeFromString(UserGoal.serializer(),value)
    }

    @TypeConverter
    fun fromLocalDate(date: LocalDate): String {
        return Json.encodeToString(LocalDate.serializer(),date)
    }

    @TypeConverter
    fun toLocalDate(value : String): LocalDate{
        return Json.decodeFromString(LocalDate.serializer(),value)
    }

    @TypeConverter
    fun fromFoodServing(food: FoodServing): String{
        return Json.encodeToString(FoodServing.serializer(),food)
    }

    @TypeConverter
    fun toFoodServing(value: String): FoodServing{
        return Json.decodeFromString(FoodServing.serializer(),value)
    }

    @TypeConverter
    fun fromListFoodServing(foods: List<FoodServing>): String{
        return foods.toString()
    }

    @TypeConverter
    fun toListFoodServing(value: String): List<FoodServing>{
        val result = ArrayList<FoodServing>()
        val split =value.replace("[","").replace("]","").replace(" ","").split(",")
        for (n in split) {
            try {
                result.add(Json.decodeFromString(FoodServing.serializer(),n))
            } catch (e: Exception) {

            }
        }
        return result
    }

    @TypeConverter
    fun fromUserNutrients(value: UserNutrients): String{
        return Json.encodeToString(UserNutrients.serializer(), value)
    }

    @TypeConverter
    fun toUserNutrients(value: String): UserNutrients{
        return Json.decodeFromString(UserNutrients.serializer(), value)
    }
}