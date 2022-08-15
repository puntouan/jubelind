package com.jubel.jubelind

import com.jubel.jubelind.products.domain.ProductRepository
import com.jubel.jubelind.recipes.domain.RecipeRepository
import com.jubel.jubelind.shared.infrastructure.SQLiteModule

fun main(){

    val injector = JubelIND().start(SQLiteModule())

//    val productRepository = injector.getInstance(ProductRepository::class.java)
//    ProductsData().get().forEach {
//        productRepository.createProduct(it)
//    }


//    val recipeRepository = injector.getInstance(RecipeRepository::class.java)
//    RecipesData().get(productRepository).forEach {
//        recipeRepository.createRecipe(it)
//    }

}


