package nutra.log.backend.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
data class OpenFoodFact(
    val code: String,
    val product: Product,
    val status: Long,
){
    @Serializable
    data class Product(
        val code: String? = null,
        val product_name: String,
        val nutriments: Nutriments
    )

    fun toFood(): Food{
        return Food(ObjectId(code), this.product.product_name, "", this.product.nutriments.energy_kcal,this.product.nutriments.proteins, this.product.nutriments.carbohydrates, this.product.nutriments.fat)
    }
}


@Serializable
data class OpenFoodFactSearch(
    val count: Int?,
    val page: Int,
    val page_count: Int,
    val page_size:Int,
    val products: List<SearchProduct>,
){
    @Serializable
    data class SearchProduct(
        val code: String? = null,
        val _id: String,
        val product_name: String = "",
        val nutriments: Nutriments
    ){
        fun toFood(): Food = Food(ObjectId(_id),product_name,"",nutriments.energy_kcal,nutriments.proteins,nutriments.carbohydrates,nutriments.fat)
    }
}



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