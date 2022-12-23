package com.jubel.jubelind.products.infrastructure

import com.jubel.jubelind.products.application.ProductGetById
import com.jubel.jubelind.products.domain.ProductMother
import com.jubel.jubelind.products.infrastructure.dtos.ProductDto
import com.jubel.jubelind.products.infrastructure.dtos.ProductDtoMapper
import com.jubel.jubelind.shared.infrastructure.NotFoundException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import spark.Request

@ExtendWith(MockitoExtension::class)
class ProductRetrievalControllerShould {

    @Mock
    lateinit var request: Request

    @Mock
    lateinit var productGetById: ProductGetById

    private val productDtoMapper = ProductDtoMapper()

    @Test
    fun `get an existing product by id`(){
        // given
        val existingProduct = ProductMother.instance()
        `when`(request.params(":productId"))
            .thenReturn(existingProduct.id.value)
        `when`(productGetById.getById(existingProduct.id.value))
            .thenReturn(existingProduct)

        // when
        val result = ProductRetrievalController(productGetById, productDtoMapper)
            .getById(request).toString()

        // then
        val productResult = productDtoMapper.mapFromDtoToDomain(
            Json.decodeFromString<ProductDto>(result)
        )
        assertEquals(existingProduct, productResult)
    }

    @Test
    fun `warn when trying to get a non-existing product by id`() {
        // given
        `when`(request.params(":productId"))
            .thenReturn("non-existing-id")
        `when`(productGetById.getById("non-existing-id"))
            .thenReturn(null)

        // then
        assertThrows<NotFoundException> {
            // when
            ProductRetrievalController(productGetById, productDtoMapper).getById(request)
        }
    }


}