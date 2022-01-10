package com.jubel.jubelind

import com.jubel.jubelind.products.domain.ProductRepository
import com.jubel.jubelind.products.infrastructure.ProductInMemoryRepository
import com.jubel.jubelind.recipes.domain.RecipeRepository

fun main(){
    val injector = JubelIND().start()

    val productRepository = injector.getInstance(ProductRepository::class.java) as ProductInMemoryRepository
    ProductsData().get().forEach {
        productRepository.createProduct(it)
    }


    val recipeRepository = injector.getInstance(RecipeRepository::class.java)
    RecipesData().get(productRepository).forEach {
        recipeRepository.createRecipe(it)
    }

}


