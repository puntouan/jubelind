package com.jubel.jubelind.products.infrastructure

import com.google.inject.Inject
import com.jubel.jubelind.products.application.ProductListAll
import com.jubel.jubelind.products.infrastructure.dtos.mapFromDomainToDto
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import spark.kotlin.get

class ProductListAllController @Inject constructor(
    private val productListAll: ProductListAll
){

    init {
        get("/product"){
            listAll()
        }
    }

    fun listAll(): Any{
        return Json.encodeToString(productListAll.listAll().mapFromDomainToDto())
    }

}