package com.jubel.jubelind.recipes.application

import com.google.inject.Inject
import com.jubel.jubelind.recipes.domain.RecipeRepository

class SetGramsOfProduct @Inject constructor(
    private val recipeRepository: RecipeRepository
) {

    fun run(recipeId: String, productId: String, grams: Int){
        return recipeRepository.setGramsToProduct(recipeId, productId, grams)
    }

}