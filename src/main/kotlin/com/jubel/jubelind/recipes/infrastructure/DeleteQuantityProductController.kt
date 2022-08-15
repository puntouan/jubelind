package com.jubel.jubelind.recipes.infrastructure

import com.google.inject.Inject
import com.jubel.jubelind.recipes.application.DeleteQuantityProduct
import com.jubel.jubelind.recipes.application.SetGramsOfProduct
import spark.kotlin.delete
import spark.kotlin.post

class DeleteQuantityProductController @Inject constructor(
    private val deleteQuantityProduct: DeleteQuantityProduct
) {

    init {
        delete("/recipe/:recipeId/product/:productId"){
            val recipeId: String = request.params(":recipeId")
            val productId: String = request.params(":productId")
            deleteQuantityProduct(recipeId, productId)
        }
    }

    fun deleteQuantityProduct(recipeId: String, productId: String){
        deleteQuantityProduct.run(recipeId, productId)
    }

}