package nutra.log.backend.models

import org.bson.types.ObjectId

data class FoodServing(
    val foodId: ObjectId,
    val numberOfServings: Double,
)