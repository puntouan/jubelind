package com.jubel.jubelind.products.infrastructure

import com.google.inject.Inject
import com.jubel.jubelind.products.application.ProductUpdate
import com.jubel.jubelind.products.domain.NonExistingProductException
import com.jubel.jubelind.products.domain.ProductDataToUpdate
import com.jubel.jubelind.shared.infrastructure.BadRequestException
import com.jubel.jubelind.shared.infrastructure.NotFoundException
import com.jubel.jubelind.shared.infrastructure.decodeFromString
import com.jubel.jubelind.shared.infrastructure.encodeToString
import kotlinx.serialization.SerializationException
import spark.Request
import spark.kotlin.post

class ProductUpdateController @Inject constructor(
    private val productUpdate: ProductUpdate
) {
    init {
        post("/product/:productId"){
            updateProduct(request)
        }
    }

    fun updateProduct(request: Request): Any{
        val productId: String = request.params(":productId")
        val productDataToUpdate = try{
            ProductDataToUpdate.decodeFromString(request.body())
        }catch (ex: SerializationException){
            throw BadRequestException("Failed to parse request. ${ex.message} ", ex)
        }
        val updatedProduct = try{
            productUpdate.update(productId, productDataToUpdate)
        }catch (ex: NonExistingProductException){
            throw NotFoundException("No such product: $productId")
        }
        return updatedProduct.encodeToString()
    }

}