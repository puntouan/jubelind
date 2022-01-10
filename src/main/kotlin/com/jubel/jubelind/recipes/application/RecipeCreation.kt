package com.jubel.jubelind.recipes.application

import com.google.inject.Inject
import com.jubel.jubelind.recipes.domain.Recipe
import com.jubel.jubelind.recipes.domain.RecipeRepository
import com.jubel.jubelind.recipes.domain.RecipeToCreate

class RecipeCreation @Inject constructor(
    private val recipeRepository: RecipeRepository
) {

    fun run(recipeToCreate: RecipeToCreate): Recipe {
        return recipeRepository.createRecipe(Recipe.fromRecipeToCreate(recipeToCreate))
    }

}