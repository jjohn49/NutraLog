package models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class UserKMM(
    @PrimaryKey(autoGenerate = false) var id: String,
    //token is only for storing in localdb
    var token: String = "",
    var email:String,
    var password:String,
    var userGoals: UserGoal? = null,
    @Ignore var days: List<Day> = listOf()
){
    constructor(id: String, email: String, password: String, userGoals: UserGoal?): this(id,"",email,password,null,
        listOf()
    )
}
