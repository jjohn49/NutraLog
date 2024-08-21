package nutra.log.backend.responses

import nutra.log.backend.models.Day
import nutra.log.backend.models.DayKMM
import nutra.log.backend.models.FoodServingKMM


data class CreateDayResponse(
    override val success: Boolean,
    override val body: DayKMM?,
    override val message: String,
    override val request: Any?
): GenericResponse<Any?>
data class GetDayResponse(
    override val success: Boolean,
    override val body: DayKMM?,
    override val message: String,
    override val request: String?
): GenericResponse<String?>

data class AddFoodToDayResponse(
    override val success: Boolean,
    override val body: FoodServingKMM?,
    override val message: String,
    override val request: String?
):GenericResponse<String?>