package models

import kotlinx.serialization.Serializable

@Serializable
data class Food(
    var id: String,
    var name: String,
    var servingSize: String,
    var calories: Double?,
    var proteinGrams:Double?,
    var carbGrams:Double?,
    var fatGrams:Double?,
    var brand: String = ""
){
    fun toUserNutrients(): UserNutrients{
        if(calories == null || proteinGrams == null || carbGrams == null || fatGrams == null){
            throw Error("Failed to convert food: ${name} to UserNutrient.  One of the necessary macros was null for food.")
        }

        return UserNutrients(calories!!, proteinGrams!!, carbGrams!!, fatGrams!!)
    }
}
