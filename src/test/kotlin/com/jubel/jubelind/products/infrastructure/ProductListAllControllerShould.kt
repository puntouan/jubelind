package com.jubel.jubelind.products.infrastructure

import com.jubel.jubelind.products.application.ProductListAll
import com.jubel.jubelind.products.domain.ProductMother
import com.jubel.jubelind.products.infrastructure.dtos.ProductDto
import com.jubel.jubelind.products.infrastructure.dtos.ProductDtoMapper
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ProductListAllControllerShould {

    @Mock
    lateinit var productListAll: ProductListAll

    private val productDtoMapper = ProductDtoMapper()

    @Test
    fun `return all existing products`(){

        // given
        val existingProducts = ProductMother.instances(5)

        Mockito.`when`(productListAll.listAll())
            .thenReturn(existingProducts)

        // when
        val result = ProductListAllController(productListAll, productDtoMapper)
            .listAll().toString()

        // then
        val productResult = productDtoMapper.mapFromDtoToDomain(
            Json.decodeFromString<List<ProductDto>>(result)
        )
        Assertions.assertEquals(existingProducts, productResult)
    }

}