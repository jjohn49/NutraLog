package nutra.log.backend.models

data class FoodSearch(
    val count: Int?,
    val page: Int,
    val page_count: Int,
    val page_size: Int,
    val products: List<Food>
)