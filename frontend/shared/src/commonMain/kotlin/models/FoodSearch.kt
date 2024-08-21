package models

import kotlinx.serialization.Serializable

@Serializable
data class FoodSearch(
    val count: Int?,
    val page: Int,
    val page_count: Int,
    val page_size: Int,
    val products: List<Food>
)
