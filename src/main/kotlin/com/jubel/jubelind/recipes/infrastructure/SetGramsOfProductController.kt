package com.jubel.jubelind.recipes.infrastructure

import com.google.inject.Inject
import com.jubel.jubelind.recipes.application.SetGramsOfProduct
import spark.kotlin.post

class SetGramsOfProductController @Inject constructor(
    private val setGramsOfProduct: SetGramsOfProduct
) {

    init {
        post("/recipe/:recipeId/product/:productId"){
            val recipeId: String = request.params(":recipeId")
            val productId: String = request.params(":productId")
            val grams = request.queryParams("grams").toInt()
            setGramsOfProduct(recipeId, productId, grams)
        }
    }

    fun setGramsOfProduct(recipeId: String, productId: String, grams: Int){
        setGramsOfProduct.run(recipeId, productId, grams)
    }

}