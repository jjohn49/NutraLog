package nutra.log.backend.models

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference

@Document("user")
data class User(
    @Id
    val id: ObjectId = ObjectId(),
    val userGoals: UserGoals,

    @DocumentReference(lazy = true)
    val currentDay: Day

)
data class UserGoals(
    val calories: Int,
    val proteinGrams: Int,
    val carbGrams: Int,
    val fatGrams:Int
)


