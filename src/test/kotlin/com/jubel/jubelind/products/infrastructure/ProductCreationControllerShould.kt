package com.jubel.jubelind.products.infrastructure

import com.jubel.jubelind.products.application.ProductCreation
import com.jubel.jubelind.products.domain.ProductMother
import com.jubel.jubelind.products.domain.ProductToCreate
import com.jubel.jubelind.products.domain.ProductToCreateMother
import com.jubel.jubelind.products.infrastructure.dtos.ProductDto
import com.jubel.jubelind.products.infrastructure.dtos.ProductDtoMapper
import com.jubel.jubelind.products.infrastructure.dtos.ProductToCreateDtoMapper
import com.jubel.jubelind.shared.infrastructure.BadRequestException
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
class ProductCreationControllerShould {

    private fun <T> kAny(type: Class<T>): T = any(type)

    @Mock
    lateinit var request: Request

    @Mock
    lateinit var productCreation: ProductCreation

    private val productToCreateDtoMapper = ProductToCreateDtoMapper()

    private val productDtoMapper = ProductDtoMapper()


    @Test
    fun `create a new product`(){
        val createdProduct = ProductMother.instance()
        //given
        val jsonRequest = Json.encodeToString(ProductToCreateMother.instanceDto())
        `when`(request.body()).thenReturn(jsonRequest)
        `when`(productCreation.create(kAny(ProductToCreate::class.java))).thenReturn(createdProduct)

        // when
        val result = ProductCreationController(
            productCreation, productToCreateDtoMapper, productDtoMapper
        ).createNewProduct(request).toString()
        val resultProduct = productDtoMapper.mapFromDtoToDomain(
            Json.decodeFromString<ProductDto>(result)
        )

        // then
        Assertions.assertEquals(createdProduct, resultProduct)
    }

    @Test
    fun `not allow create a new product given an invalid format`(){

        // given
        val jsonRequest = """
            {"name":"name",
            "calories":"INVALID FORMAT",
            "protein":"IT SHOULD",
            "fat":"BE",
            "carbohydrates":"NUMERIC"
            }
        """.trimIndent()
        `when`(request.body()).thenReturn(jsonRequest)

        // then
        assertThrows<BadRequestException>{
            // when
            ProductCreationController(
                productCreation, productToCreateDtoMapper, productDtoMapper
            ).createNewProduct(request)
        }

    }

}