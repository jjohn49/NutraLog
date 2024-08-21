package models

import kotlinx.serialization.Serializable

@Serializable
data class UserGoal(
    var calories: Int = 2000,
    var proteinGrams: Int = 200,
    var carbGrams: Int = 200,
    var fatGrams:Int = 100
){
}
