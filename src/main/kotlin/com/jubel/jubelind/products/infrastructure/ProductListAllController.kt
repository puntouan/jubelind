package com.jubel.jubelind.products.infrastructure

import com.google.inject.Inject
import com.jubel.jubelind.products.application.ProductGetById
import com.jubel.jubelind.products.application.ProductListAll
import com.jubel.jubelind.shared.infrastructure.NotFoundException
import com.jubel.jubelind.shared.infrastructure.encodeToString
import spark.Request
import spark.kotlin.get

class ProductListAllController @Inject constructor(
    private val productListAll: ProductListAll
){

    init {
        get("/product"){
            productListAll.listAll().encodeToString()
        }
    }

}