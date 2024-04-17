package nutra.log.backend.models

import com.mongodb.DBRef
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

    val userId: ObjectId,

    val date: Date = Date(),

    val foodsEaten: List<FoodServing> = listOf()
){
    constructor(userId:String): this(userId = ObjectId(userId))
}

data class FoodServing(
    val foodId: ObjectId,
    val numberOfServings: Float
)
