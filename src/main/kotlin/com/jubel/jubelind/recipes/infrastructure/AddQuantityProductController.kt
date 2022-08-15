package com.jubel.jubelind.recipes.infrastructure

import com.google.inject.Inject
import com.jubel.jubelind.recipes.application.AddQuantityProduct
import com.jubel.jubelind.recipes.infrastructure.dtos.QuantityProductDto
import com.jubel.jubelind.shared.infrastructure.BadRequestException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import spark.Request
import spark.kotlin.put

class AddQuantityProductController @Inject constructor(
    private val addQuantityProduct: AddQuantityProduct
) {

    init {
        put("/recipe/:recipeId/quantity-product"){
            addQuantityProduct(request)
        }
    }

    fun addQuantityProduct(request: Request){
        val recipeId: String = request.params(":recipeId")!!
        val quantityProductDto = try{
            Json.decodeFromString<QuantityProductDto>(request.body())
        }catch (ex: SerializationException){
            throw BadRequestException("Failed to parse request. ${ex.message} ", ex)
        }
        addQuantityProduct.run(recipeId, quantityProductDto.productId, quantityProductDto.grams)
    }

}