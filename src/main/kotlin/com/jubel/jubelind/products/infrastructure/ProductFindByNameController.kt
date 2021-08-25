package com.jubel.jubelind.products.infrastructure

import com.google.inject.Inject
import com.jubel.jubelind.products.application.ProductFindByName
import com.jubel.jubelind.products.application.ProductGetById
import com.jubel.jubelind.products.application.ProductListAll
import com.jubel.jubelind.shared.infrastructure.NotFoundException
import com.jubel.jubelind.shared.infrastructure.encodeToString
import spark.Request
import spark.kotlin.get

class ProductFindByNameController @Inject constructor(
    private val productFindByName: ProductFindByName
){

    init {
        get("/product/search/name"){
            val str = request.queryParams("key")
            findByName(str)
        }
    }

    fun findByName(str: String): Any{
        return productFindByName.findByName(str).encodeToString()
    }

}