package models

data class User(
    val id: String,
    val email:String,
    val password:String,
    var userGoals: UserGoal? = null,
    val days: ArrayList<String> = arrayListOf()
)
