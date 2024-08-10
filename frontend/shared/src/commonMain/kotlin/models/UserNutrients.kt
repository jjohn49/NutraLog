package models

data class UserNutrients(
    val calories: Double = 0.0,
    val proteinGrams: Double = 0.0,
    val carbGrams: Double = 0.0,
    val fatGrams: Double = 0.0
){
    fun multiplyByServings(servings: Double): UserNutrients{
        return UserNutrients(calories * servings, proteinGrams * servings, carbGrams * servings, fatGrams * servings)
    }
}
