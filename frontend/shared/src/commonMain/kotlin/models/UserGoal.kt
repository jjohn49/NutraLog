package models

import kotlinx.serialization.Serializable

@Serializable
data class UserGoal(
    val calories: Int = 2000,
    val proteinGrams: Int = 200,
    val carbGrams: Int = 200,
    val fatGrams:Int = 100
){
}
