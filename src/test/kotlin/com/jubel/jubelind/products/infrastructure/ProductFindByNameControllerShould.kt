package com.jubel.jubelind.products.infrastructure

import com.jubel.jubelind.products.application.ProductFindByName
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
class ProductFindByNameControllerShould {

    @Mock
    lateinit var productFindByName: ProductFindByName

    private val productDtoMapper = ProductDtoMapper()

    @Test
    fun `return matching products`(){

        // given
        val matchingProducts = ProductMother.instances(5)

        Mockito.`when`(productFindByName.findByName("str"))
            .thenReturn(matchingProducts)

        // when
        val result = ProductFindByNameController(productFindByName, productDtoMapper)
            .findByName("str").toString()

        // then
        val productResult = productDtoMapper.mapFromDtoToDomain(
            Json.decodeFromString<List<ProductDto>>(result)
        )
        Assertions.assertEquals(matchingProducts, productResult)
    }

}