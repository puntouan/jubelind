package com.jubel.jubelind.products.infrastructure

import com.google.inject.Inject
import com.jubel.jubelind.products.application.ProductCreation
import com.jubel.jubelind.products.infrastructure.dtos.*
import com.jubel.jubelind.shared.infrastructure.BadRequestException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import spark.Request
import spark.kotlin.post

class ProductCreationController @Inject constructor(
    private val productCreation: ProductCreation,
    private val productToCreateDtoMapper: ProductToCreateDtoMapper,
    private val productDtoMapper: ProductDtoMapper
) {
    init {
        post("/product"){
            createNewProduct(request)
        }
    }

    fun createNewProduct(request: Request): Any{
        val productToCreate = try{
            productToCreateDtoMapper.mapFromDtoToDomain(Json.decodeFromString(request.body()))
        }catch (ex: SerializationException){
            throw BadRequestException("Failed to parse request. ${ex.message} ", ex)
        }
        val createdProduct = productCreation.create(productToCreate)
        return Json.encodeToString(productDtoMapper.mapFromDomainToDto(createdProduct))
    }

}