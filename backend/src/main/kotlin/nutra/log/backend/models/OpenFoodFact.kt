package nutra.log.backend.models

import kotlinx.serialization.Serializable

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
        return Food(code, this.product.product_name, "", this.product.nutriments.energy_kcal,this.product.nutriments.proteins, this.product.nutriments.carbohydrates, this.product.nutriments.fat)
    }
}


