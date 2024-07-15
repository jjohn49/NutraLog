package models

data class UserNutrients(
    val calories: Double,
    val proteinGrams: Double,
    val carbGrams: Double,
    val fatGrams: Double
){
    fun multiplyByServings(servings: Double): UserNutrients{
        return UserNutrients(calories * servings, proteinGrams * servings, carbGrams * servings, fatGrams * servings)
    }
}
