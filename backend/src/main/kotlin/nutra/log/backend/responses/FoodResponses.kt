package nutra.log.backend.responses

import nutra.log.backend.models.FoodSearch

data class FoodSearchResponse(
    override val success: Boolean,
    override val body: FoodSearch?,
    override val message: String,
    override val request: Any?
) :GenericResponse<Any?>