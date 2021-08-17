package com.jubel.jubelind.products.application

import com.jubel.jubelind.products.domain.ProductForCreateMother
import com.jubel.jubelind.products.domain.ProductMother
import com.jubel.jubelind.products.domain.ProductRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class ProductCreationShould {

    @Mock
    private lateinit var productRepository: ProductRepository

    @Test
    fun `create a new product`() {
        val newProduct = ProductForCreateMother.instance()

        val productCreated = ProductCreation(productRepository).create(newProduct)

        assertThat(productCreated.id).isNotNull
        verify(productRepository).createProduct(productCreated)
    }

}