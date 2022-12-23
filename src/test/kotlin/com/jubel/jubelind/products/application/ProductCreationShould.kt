package com.jubel.jubelind.products.application

import com.jubel.jubelind.products.domain.Product
import com.jubel.jubelind.products.domain.ProductRepository
import com.jubel.jubelind.products.domain.ProductToCreateMother
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class ProductCreationShould {

    private fun <T> kAny(type: Class<T>): T = any(type)

    @Mock
    private lateinit var productRepository: ProductRepository

    @Test
    fun `create a new product`() {
        val productToCreate = ProductToCreateMother.instance()
        `when`(productRepository.createProduct(kAny(Product::class.java))).thenAnswer {it.arguments[0]}

        val productCreated = ProductCreation(productRepository).create(productToCreate)

        assertThat(productCreated.id).isNotNull
        verify(productRepository).createProduct(productCreated)
    }

}