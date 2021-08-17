package com.jubel.jubelind.products.infrastructure

import com.google.inject.Inject
import com.jubel.jubelind.products.application.ProductDeleteById
import com.jubel.jubelind.products.domain.NonExistingProductException
import com.jubel.jubelind.shared.infrastructure.NotFoundException
import spark.Request
import spark.Response
import spark.kotlin.delete

class ProductDeletionController @Inject constructor(
    private val productDeleteById: ProductDeleteById
){

    init {
        delete("/product/:productId"){
            delete(request, response)
        }
    }

    fun delete(request: Request, response: Response){
        val productId = request.params(":productId")
        try{
            productDeleteById.deleteById(productId)
            response.status(204)
        }catch (ex: NonExistingProductException){
            throw NotFoundException("No such product: $productId")
        }
    }

}