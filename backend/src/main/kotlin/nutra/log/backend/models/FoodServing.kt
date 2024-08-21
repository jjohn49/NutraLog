package nutra.log.backend.models

import org.bson.types.ObjectId

data class FoodServing(
    val foodId: String = ObjectId.get().toString(),
    val numberOfServings: Double,
)

data class FoodServingKMM(
    val food: Food,
    val numberOfServings: Double
){
    fun toFoodServing(): FoodServing{
        return FoodServing(food.id, numberOfServings)
    }
}