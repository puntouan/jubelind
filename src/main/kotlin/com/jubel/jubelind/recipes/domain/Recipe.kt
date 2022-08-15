package com.jubel.jubelind.recipes.domain

class Recipe(
    val id: RecipeId,
    val name: String,
    val quantityProducts: List<ProductGrams>
    ){

    val grams = quantityProducts.sumOf { it.grams }
    val absolutCalories = quantityProducts.sumOf { it.absolutCalories }
    private val calsOfMacros = quantityProducts.sumOf { it.calsOfMacros }

    val unknownCals = absolutCalories - calsOfMacros
    val unknownCalsPercentage = (unknownCals * 100) / absolutCalories

    val proteinGrams = quantityProducts.sumOf { it.proteinGrams }
    val proteinGramsPercentage = (proteinGrams * 100) / grams
    val proteinCals = quantityProducts.sumOf { it.proteinCals }
    val proteinCalsPercentage = (proteinCals * 100) / absolutCalories

    val fatGrams = quantityProducts.sumOf { it.fatGrams }
    val fatGramsPercentage = (fatGrams * 100) / grams
    val fatCals = quantityProducts.sumOf { it.fatCals }
    val fatCalsPercentage = (fatCals * 100) / absolutCalories

    val carbohydratesGrams = quantityProducts.sumOf { it.carbohydratesGrams }
    val carbohydratesGramsPercentage = (carbohydratesGrams * 100) / grams
    val carbohydratesCals = quantityProducts.sumOf { it.carbohydratesCals }
    val carbohydratesCalsPercentage = (carbohydratesCals * 100) / absolutCalories

    companion object{
        fun fromRecipeToCreate(recipeToCreate: RecipeToCreate): Recipe{
            return Recipe(RecipeId(), recipeToCreate.name, recipeToCreate.quantityProducts)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Recipe

        if (id != other.id) return false
        if (name != other.name) return false
        if (quantityProducts != other.quantityProducts) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + quantityProducts.hashCode()
        return result
    }


}