package nutra.log.backend.models

import kotlinx.serialization.Serializable
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("Food")
@Serializable
data class Food(

    //This will be FDIC #
    @Id
    val id: String = ObjectId.get().toString(),
    val name: String,
    val servingSize: String,
    val calories: Double?,

    //would name this gramsOf**** but its easier to id this way
    val proteinGrams:Double?,
    val carbGrams:Double?,
    val fatGrams:Double?,

    //Leave all Optionals at the End
    val brand: String = ""

)

