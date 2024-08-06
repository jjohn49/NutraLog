package response

import kotlinx.serialization.Serializable
import models.Day

@Serializable
data class GetAllDaysResponse(
    override val success: Boolean,
    override val body: List<Day>,
    override val message: String,
    override val request: String?
): GenericResponse<String?>

@Serializable
data class CreateDayResponse(
    override val success: Boolean,
    override val body: Day?,
    override val message: String,
    override val request: String?
): GenericResponse<String?>