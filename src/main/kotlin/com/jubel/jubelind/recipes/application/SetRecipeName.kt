package com.jubel.jubelind.recipes.application

import com.google.inject.Inject
import com.jubel.jubelind.recipes.domain.RecipeRepository

class SetRecipeName @Inject constructor(
    private val recipeRepository: RecipeRepository
) {

    fun run(recipeId: String, name: String){
        return recipeRepository.setRecipeName(recipeId, name)
    }

}