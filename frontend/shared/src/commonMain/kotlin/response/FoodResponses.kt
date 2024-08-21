package response

import kotlinx.serialization.Serializable
import models.FoodSearch

@Serializable
data class FoodSearchResponse(
    override val success: Boolean,
    override val body: FoodSearch?,
    override val message: String,
    override val request: String?
):GenericResponse<Any?>