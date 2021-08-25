package com.jubel.jubelind.products.infrastructure

import com.jubel.jubelind.products.application.ProductGetById
import com.jubel.jubelind.products.domain.ProductMother
import com.jubel.jubelind.shared.infrastructure.NotFoundException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import spark.Request
import java.util.*

@ExtendWith(MockitoExtension::class)
class ProductRetrievalControllerShould {

    @Mock
    lateinit var request: Request

    @Mock
    lateinit var productGetById: ProductGetById

    @Test
    fun `get an existing product by id`(){
        // given
        val existingProduct = ProductMother.instance()
        `when`(request.params(":productId"))
            .thenReturn(existingProduct.id.value)
        `when`(productGetById.getById(existingProduct.id.value))
            .thenReturn(Optional.of(existingProduct))

        // when
        val result = ProductRetrievalController(productGetById)
            .getById(request).toString()

        // then
        val productResult = ProductMother.fromJson2Product(result)
        assertEquals(existingProduct, productResult)
    }

    @Test
    fun `warn when trying to get a non-existing product by id`() {
        // given
        `when`(request.params(":productId"))
            .thenReturn("non-existing-id")
        `when`(productGetById.getById("non-existing-id"))
            .thenReturn(Optional.empty())

        // then
        assertThrows<NotFoundException> {
            // when
            ProductRetrievalController(productGetById).getById(request)
        }
    }


}