package nutra.log.backend.responses

import nutra.log.backend.models.Day
import nutra.log.backend.models.DayKMM


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