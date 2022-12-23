package com.jubel.jubelind.products.infrastructure

import com.google.inject.Inject
import com.jubel.jubelind.products.application.ProductFindByName
import com.jubel.jubelind.products.infrastructure.dtos.ProductDtoMapper
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import spark.kotlin.get

class ProductFindByNameController @Inject constructor(
    private val productFindByName: ProductFindByName,
    private val productDtoMapper: ProductDtoMapper
){

    init {
        get("/product/search/name"){
            val str = request.queryParams("key")
            findByName(str)
        }
    }

    fun findByName(str: String): Any{
        return Json.encodeToString(
            productDtoMapper.mapFromDomainToDto(
                productFindByName.findByName(str)
            )
        )
    }

}