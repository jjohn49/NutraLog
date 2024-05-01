package nutra.log.backend.models

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference

@Document("user")
data class User(
    @Id
    val id: String,
    val email:String,
    val password:String,
    val userGoals: UserGoal? = null,
    val days: ArrayList<ObjectId> = arrayListOf()
)


