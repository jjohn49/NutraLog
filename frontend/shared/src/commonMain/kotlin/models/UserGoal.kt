package models

import kotlinx.serialization.Serializable

@Serializable
data class UserGoal(
    val calories: Int,
    val proteinGrams: Int,
    val carbGrams: Int,
    val fatGrams:Int
){
}
