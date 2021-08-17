package com.jubel.jubelind.products.infrastructure

import com.jubel.jubelind.products.application.ProductDeleteById
import com.jubel.jubelind.products.domain.NonExistingProductException
import com.jubel.jubelind.shared.infrastructure.BadRequestException
import com.jubel.jubelind.shared.infrastructure.NotFoundException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import spark.Request
import spark.Response

@ExtendWith(MockitoExtension::class)
class ProductDeletionControllerShould {

    @Mock
    lateinit var request: Request

    @Mock
    lateinit var response: Response

    @Mock
    lateinit var productDeleteById: ProductDeleteById

    @Test
    fun `delete an existing product`(){
        // given
        `when`(request.params(":productId")).thenReturn("1234-5678")

        // when
        ProductDeletionController(productDeleteById).delete(request, response)

        // then
        verify(productDeleteById).deleteById("1234-5678")
        verify(response).status(204)
    }

    @Test
    fun `warn when trying to delete an non-existing product`(){
        // given
        `when`(request.params(":productId")).thenReturn("non-existing-id")
        `when`(productDeleteById.deleteById("non-existing-id")).thenThrow(NonExistingProductException::class.java)

        // then
        assertThrows<NotFoundException>{
            // when
            ProductDeletionController(productDeleteById).delete(request, response)
        }
    }

}