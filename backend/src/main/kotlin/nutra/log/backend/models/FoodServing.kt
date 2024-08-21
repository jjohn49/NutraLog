package nutra.log.backend.models

import org.bson.types.ObjectId

data class FoodServing(
    var foodId: String = ObjectId.get().toString(),
    var numberOfServings: Double,
)

data class FoodServingKMM(
    val food: Food,
    val numberOfServings: Double
){
    fun toFoodServing(): FoodServing{
        return FoodServing(food.id, numberOfServings)
    }
}