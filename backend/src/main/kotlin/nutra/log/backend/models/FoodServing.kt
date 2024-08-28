package nutra.log.backend.models

import kotlinx.serialization.Serializable
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.DBRef

@Serializable
data class FoodServing(
    @DBRef
    val food: Food,
    val numberOfServings: Double
)