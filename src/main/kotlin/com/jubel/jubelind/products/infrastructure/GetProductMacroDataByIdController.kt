package com.jubel.jubelind.products.infrastructure

import com.google.inject.Inject
import com.jubel.jubelind.products.application.ProductGetById
import com.jubel.jubelind.products.infrastructure.dtos.mapFromDomainToMacroDataDto
import com.jubel.jubelind.shared.infrastructure.NotFoundException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import spark.kotlin.get

class GetProductMacroDataByIdController @Inject constructor(
    private val productGetById: ProductGetById
) {

    init {
        get("/product/:productId/macro-data"){
            val productId: String = request.params(":productId")
            getProductMacroDataById(productId)
        }
    }

    fun getProductMacroDataById(productId: String): Any{
        val product = try{
            productGetById.getById(productId)!!
        }catch (ex: SerializationException){
            throw NotFoundException("No such product with id: $productId")
        }
        return Json.encodeToString(product.mapFromDomainToMacroDataDto())
    }

}