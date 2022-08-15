package com.jubel.jubelind.recipes.application

import com.google.inject.Inject
import com.jubel.jubelind.products.domain.ProductRepository
import com.jubel.jubelind.recipes.domain.RecipeRepository

class AddQuantityProduct @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val productRepository: ProductRepository
) {

    fun run(recipeId: String, productId: String, grams: Int){
        val product = productRepository.validateAndGetById(productId)
        recipeRepository.addQuantityProduct(recipeId, product, grams)
    }

}