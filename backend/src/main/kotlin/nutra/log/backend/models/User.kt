package nutra.log.backend.models

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference

@Document("user")
data class User(
    @Id
    val id: ObjectId = ObjectId(),
    val userGoals: UserGoal?,
    val days: ArrayList<ObjectId> = arrayListOf()
)


