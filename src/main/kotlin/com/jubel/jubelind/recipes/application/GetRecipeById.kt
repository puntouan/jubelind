package com.jubel.jubelind.recipes.application

import com.google.inject.Inject
import com.jubel.jubelind.recipes.domain.Recipe
import com.jubel.jubelind.recipes.domain.RecipeRepository

class GetRecipeById @Inject constructor(
    private val recipeRepository: RecipeRepository
) {

    fun run(recipeId: String): Recipe{
        return recipeRepository.getById(recipeId)!!
    }

}