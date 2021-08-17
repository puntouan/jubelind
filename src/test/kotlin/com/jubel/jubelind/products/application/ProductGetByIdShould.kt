package com.jubel.jubelind.products.application

import com.jubel.jubelind.products.domain.ProductMother
import com.jubel.jubelind.products.domain.ProductRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class ProductGetByIdShould {

    @Mock
    private lateinit var productRepository: ProductRepository

    @Test
    fun `give an existing product by id`(){
        val existingProduct = ProductMother.instance()
        `when`(productRepository.getById(existingProduct.id.value)).thenReturn(Optional.of(existingProduct))

        val resultProduct = ProductGetById(productRepository).getById(existingProduct.id.value).get()

        assertEquals(existingProduct, resultProduct)
    }

    @Test
    fun `give nothing for a not existing`(){

        `when`(productRepository.getById("non-existent-id")).thenReturn(Optional.empty())

        val resultProduct = ProductGetById(productRepository).getById("non-existent-id")

        assertThat(resultProduct).isEmpty

    }

}