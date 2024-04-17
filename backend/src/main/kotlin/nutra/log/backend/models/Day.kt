package nutra.log.backend.models

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.ReadOnlyProperty
import org.springframework.data.annotation.Reference
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference
import java.util.Date

@Document("day")
data class Day(
    @Id
    val id: ObjectId = ObjectId(),

    @DocumentReference(lazy = true)
    @ReadOnlyProperty
    val user: User,

    val date: Date = Date(),

    val foodsEaten: List<FoodServing> = listOf()

)

@Document("food-serving")
data class FoodServing(
    @DocumentReference(lazy = true)
    val food: Food,
    val numberOfServings: Float
)
