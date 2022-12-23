package com.jubel.jubelind.products.infrastructure

import com.google.inject.Inject
import com.jubel.jubelind.products.application.ProductUpdate
import com.jubel.jubelind.products.domain.NonExistingProductException
import com.jubel.jubelind.products.infrastructure.dtos.ProductDataToUpdateDtoMapper
import com.jubel.jubelind.products.infrastructure.dtos.ProductDtoMapper
import com.jubel.jubelind.shared.infrastructure.BadRequestException
import com.jubel.jubelind.shared.infrastructure.NotFoundException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import spark.Request
import spark.kotlin.post

class ProductUpdateController @Inject constructor(
    private val productUpdate: ProductUpdate,
    private val productDataToUpdateDtoMapper: ProductDataToUpdateDtoMapper,
    private val productDtoMapper: ProductDtoMapper
) {
    init {
        post("/product/:productId"){
            updateProduct(request)
        }
    }

    fun updateProduct(request: Request): Any{
        val productId: String = request.params(":productId")
        val productDataToUpdate = try{
            productDataToUpdateDtoMapper.mapFromDtoToDomain(
                Json.decodeFromString(request.body())
            )
        }catch (ex: SerializationException){
            throw BadRequestException("Failed to parse request. ${ex.message} ", ex)
        }
        val updatedProduct = try{
            productUpdate.update(productId, productDataToUpdate)
        }catch (ex: NonExistingProductException){
            throw NotFoundException("No such product: $productId")
        }
        return Json.encodeToString(productDtoMapper.mapFromDomainToDto(updatedProduct))
    }

}