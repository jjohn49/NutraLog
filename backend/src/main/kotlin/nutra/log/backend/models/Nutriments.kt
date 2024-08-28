package nutra.log.backend.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Nutriments(
    val carbohydrates: Double? = null,
    val carbohydrates_unit: String? = null,
    @SerialName("energy-kcal")
    val energy_kcal: Double? = null,
    @SerialName("energy-kcal_unit")
    val energy_kcal_unit: String? = null,
    val fat: Double? = null,
    val fat_unit: String? = null,
    val fiber: Double? = null,
    val fiber_unit: String? = null,
    val proteins: Double? = null,
    val proteins_unit: String? = null,
    @SerialName("saturated-fat")
    val saturated_fat: Double? = null,
    @SerialName("saturated-fat_unit")
    val saturated_fat_unit: String? = null,
    val sugars: Double? = null,
    val sugars_unit: String? = null,
)