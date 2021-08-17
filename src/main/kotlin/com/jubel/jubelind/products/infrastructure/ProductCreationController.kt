package com.jubel.jubelind.products.infrastructure

import com.google.inject.Inject
import com.jubel.jubelind.products.application.ProductCreation
import com.jubel.jubelind.products.domain.ProductToCreate
import com.jubel.jubelind.shared.infrastructure.BadRequestException
import com.jubel.jubelind.shared.infrastructure.decodeFromString
import com.jubel.jubelind.shared.infrastructure.encodeToString
import kotlinx.serialization.SerializationException
import spark.Request
import spark.kotlin.post

class ProductCreationController @Inject constructor(
    private val productProductCreation: ProductCreation
) {
    init {
        post("/product"){
            createNewProduct(request)
        }
    }

    fun createNewProduct(request: Request): Any{
        val productToCreate = try{
            ProductToCreate.decodeFromString(request.body())
        }catch (ex: SerializationException){
            throw BadRequestException("Failed to parse request. ${ex.message} ", ex)
        }
        val createdProduct = productProductCreation.create(productToCreate)
        return createdProduct.encodeToString()
    }

}