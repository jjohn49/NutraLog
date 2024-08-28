package nutra.log.backend.models

import kotlinx.serialization.Serializable
import nutra.log.backend.serializers.LocalDateSerializer
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

//@Document("day")
//data class Day(
//    @Id
//    val id: ObjectId = ObjectId(),
//
//    val userId: String,
//
//    val date: LocalDate = LocalDate.now(),
//
//    var foodsEaten: List<FoodServing> = mutableListOf()
//)



@Document("Day")
@Serializable
data class Day(
    @Id
    val id: String = ObjectId.get().toString(),
    val userId: String,
    @Serializable(with = LocalDateSerializer::class)
    val date: LocalDate,
    var foodsEaten: List<FoodServing> = listOf()
)


