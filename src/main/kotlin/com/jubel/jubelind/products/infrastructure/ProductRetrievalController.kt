package com.jubel.jubelind.products.infrastructure

import com.google.inject.Inject
import com.jubel.jubelind.products.application.ProductGetById
import com.jubel.jubelind.shared.infrastructure.NotFoundException
import com.jubel.jubelind.shared.infrastructure.encodeToString
import spark.Request
import spark.kotlin.get

class ProductRetrievalController @Inject constructor(
    private val productGetById: ProductGetById
){

    init {
        get("/product/:productId"){
            getById(request)
        }
    }

    fun getById(request: Request): Any{
        val productId = request.params(":productId")
        val product = productGetById.getById(productId)
        if (!product.isPresent){
            throw NotFoundException("No such product: $productId")
        }
        return product.get().encodeToString()
    }

}