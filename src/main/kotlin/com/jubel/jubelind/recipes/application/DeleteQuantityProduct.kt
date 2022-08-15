package com.jubel.jubelind.recipes.application

import com.google.inject.Inject
import com.jubel.jubelind.recipes.domain.RecipeRepository

class DeleteQuantityProduct @Inject constructor(
    private val recipeRepository: RecipeRepository
) {

    fun run(recipeId: String, productId: String){
        return recipeRepository.deleteQuantityProduct(recipeId, productId)
    }

}