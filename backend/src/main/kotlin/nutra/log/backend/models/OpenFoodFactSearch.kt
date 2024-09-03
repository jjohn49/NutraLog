package nutra.log.backend.models

import kotlinx.serialization.Serializable

@Serializable
data class OpenFoodFactSearch(
    val count: Int?,
    val page: Int?,
    val page_count: Int?,
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
        fun toFood(): Food = Food(
            _id,
            product_name,
            "",
            nutriments.energy_kcal,
            nutriments.proteins,
            nutriments.carbohydrates,
            nutriments.fat
        )
    }

    fun toFoodSearch(): FoodSearch {
        return FoodSearch(
            count,
            page,
            page_count,
            page_size,
            products.map { it.toFood() }
                .filter { f -> f.calories != null && f.proteinGrams != null && f.carbGrams != null && f.fatGrams != null })
    }
}