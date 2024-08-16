package nutra.log.backend.responses

import nutra.log.backend.models.Day


data class CreateDayResponse(
    override val success: Boolean,
    override val body: Day?,
    override val message: String,
    override val request: Any?
): GenericResponse<Any?>
data class GetDayResponse(
    override val success: Boolean,
    override val body: Day?,
    override val message: String,
    override val request: String?
): GenericResponse<String?>