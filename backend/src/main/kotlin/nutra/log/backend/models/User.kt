package nutra.log.backend.models

import kotlinx.serialization.Serializable
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference

@Document("user")
@Serializable
data class User(
    @Id
    val id: String,
    val email:String,
    val password:String,
    var userGoals: UserGoal? = null,
    val days: ArrayList<String> = arrayListOf()
){
    fun getSanitizedUserForLogInResponse(): User{
        return User(this.id,this.email,"",this.userGoals,this.days)
    }
}


