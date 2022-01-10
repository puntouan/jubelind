package com.jubel.jubelind.products.infrastructure

import com.jubel.jubelind.products.application.ProductUpdate
import com.jubel.jubelind.products.domain.NonExistingProductException
import com.jubel.jubelind.products.domain.ProductDataToUpdate
import com.jubel.jubelind.products.domain.ProductDataToUpdateMother
import com.jubel.jubelind.products.domain.ProductMother
import com.jubel.jubelind.products.infrastructure.dtos.ProductDto
import com.jubel.jubelind.products.infrastructure.dtos.mapFromDtoToDomain
import com.jubel.jubelind.shared.infrastructure.NotFoundException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.any
import org.mockito.junit.jupiter.MockitoExtension
import spark.Request

@ExtendWith(MockitoExtension::class)
class ProductUpdateControllerShould {

    private fun <T> kAny(type: Class<T>): T = any(type)

    @Mock
    lateinit var request: Request

    @Mock
    lateinit var productUpdate: ProductUpdate


    @Test
    fun `update a product`(){
        val updatedProduct = ProductMother.instance()
        //given
        val jsonRequest = Json.encodeToString(ProductDataToUpdateMother.instanceDto())
        `when`(request.body()).thenReturn(jsonRequest)
        `when`(request.params(":productId")).thenReturn("1234-5678")
        `when`(productUpdate.update(kAny(String::class.java), kAny(ProductDataToUpdate::class.java))).thenReturn(updatedProduct)

        // when
        val result = ProductUpdateController(productUpdate).updateProduct(request).toString()
        val resultProduct = Json.decodeFromString<ProductDto>(result).mapFromDtoToDomain()
        // then
        Assertions.assertEquals(updatedProduct, resultProduct)
    }

    @Test
    fun `warn when trying to delete an non-existing product`(){
        //given
        val jsonRequest = Json.encodeToString(ProductDataToUpdateMother.instanceDto())
        `when`(request.body()).thenReturn(jsonRequest)
        `when`(request.params(":productId")).thenReturn("1234-5678")
        `when`(productUpdate.update(kAny(String::class.java), kAny(ProductDataToUpdate::class.java)))
            .thenThrow(NonExistingProductException::class.java)

        // then
        assertThrows<NotFoundException>{
            // when
            ProductUpdateController(productUpdate).updateProduct(request)
        }
    }

}