package com.jubel.jubelind.products.infrastructure

import com.jubel.jubelind.products.application.ProductListAll
import com.jubel.jubelind.products.domain.ProductMother
import com.jubel.jubelind.products.infrastructure.dtos.ProductDto
import com.jubel.jubelind.products.infrastructure.dtos.mapFromDtoToDomain
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

    @Test
    fun `return all existing products`(){

        // given
        val existingProducts = ProductMother.instances(5)

        Mockito.`when`(productListAll.listAll())
            .thenReturn(existingProducts)

        // when
        val result = ProductListAllController(productListAll)
            .listAll().toString()

        // then
        val productResult = Json.decodeFromString<List<ProductDto>>(result).mapFromDtoToDomain()
        Assertions.assertEquals(existingProducts, productResult)
    }

}