package models

import kotlinx.serialization.Serializable

@Serializable
data class UserKMM(
    val id: String,
    val email:String,
    val password:String,
    var userGoals: UserGoal? = null,
    val days: ArrayList<String> = arrayListOf()
)
