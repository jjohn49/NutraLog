package nutra.log.backend.responses

import nutra.log.backend.models.FoodServing

data class AddFoodToDayResponse(
    override val success: Boolean,
    override val body: FoodServing?,
    override val message: String,
    override val request: String?
): GenericResponse<String?>