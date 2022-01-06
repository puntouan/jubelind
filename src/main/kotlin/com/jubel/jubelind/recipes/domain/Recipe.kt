package com.jubel.jubelind.recipes.domain

import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    val id: RecipeId,
    val name: String,
    val quantityProducts: List<ProductGrams>
    ){

    companion object{
        fun fromRecipeToCreate(recipeToCreate: RecipeToCreate): Recipe {
            return Recipe(
                RecipeId(),
                recipeToCreate.name,
                emptyList()
            )
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