package requests

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class CreateDayRequest(
    val date: LocalDate
)