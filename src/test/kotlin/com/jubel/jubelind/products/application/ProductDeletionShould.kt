package com.jubel.jubelind.products.application

import com.jubel.jubelind.products.domain.ProductRepository
import com.jubel.jubelind.products.domain.NonExistingProductException
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class ProductDeleteByIdShould {

    @Mock
    private lateinit var productRepository: ProductRepository

    @Test
    fun `delete an existing product by id`(){
        val existingProductId = "existing-product-id"

        ProductDeleteById(productRepository).deleteById(existingProductId)

        verify(productRepository).deleteById(existingProductId)
    }

    @Test
    fun `give error when product does not exist`(){
        val nonExistingProductId = "non-existing-product-id"

        `when`(productRepository.deleteById(nonExistingProductId)).thenThrow(NonExistingProductException())

        assertThrows(NonExistingProductException::class.java){
            ProductDeleteById(productRepository).deleteById(nonExistingProductId)
        }
        verify(productRepository).deleteById(nonExistingProductId)
    }

}