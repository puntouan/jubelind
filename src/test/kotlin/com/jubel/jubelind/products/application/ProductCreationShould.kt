package com.jubel.jubelind.products.application

import com.jubel.jubelind.products.domain.Product
import com.jubel.jubelind.products.domain.ProductToCreateMother
import com.jubel.jubelind.products.domain.ProductRepository
import org.assertj.core.api.Assertions.*
import org.hamcrest.core.AnyOf
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.AdditionalAnswers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.invocation.InvocationOnMock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.stubbing.Answer

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