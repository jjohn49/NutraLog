package models

import kotlinx.serialization.Serializable

@Serializable
data class UserKMM(
    var id: String,
    var email:String,
    var password:String,
    var userGoals: UserGoal? = null,
    var days: ArrayList<String> = arrayListOf()
)
