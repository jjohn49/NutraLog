package response

import kotlinx.serialization.Serializable
import models.Day
import models.FoodServing

@Serializable
data class GetAllDaysResponse(
    override val success: Boolean,
    override var body: List<Day>,
    override val message: String,
    override val request: String?
): GenericResponse<String?>

@Serializable
data class GetDayResponse(
    override val success: Boolean,
    override val body: Day?,
    override val message: String,
    override val request: String?
): GenericResponse<String?>

@Serializable
data class AddFoodToDayResponse(
    override val success: Boolean,
    override val body: FoodServing?,
    override val message: String,
    override val request: String?
):GenericResponse<String?>

@Serializable
data class DeleteFoodFromDayResponse(
    override val success: Boolean,
    override val body: Day?,
    override val message: String,
    override val request: String?
):GenericResponse<String?>