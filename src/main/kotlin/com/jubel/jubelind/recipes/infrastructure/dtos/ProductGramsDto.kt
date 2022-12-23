package com.jubel.jubelind.recipes.infrastructure.dtos

import com.google.inject.Inject
import com.google.inject.Singleton
import com.jubel.jubelind.products.infrastructure.dtos.ProductDto
import com.jubel.jubelind.products.infrastructure.dtos.ProductDtoMapper
import com.jubel.jubelind.recipes.domain.ProductGrams
import kotlinx.serialization.Serializable

@Serializable
data class ProductGramsDto(
    val product: ProductDto,
    val grams: Int
)

@Singleton
class ProductGramsDtoMapper @Inject constructor(
    private val productDtoMapper: ProductDtoMapper
){

    fun mapFromDomainToDto(source: ProductGrams):ProductGramsDto{
        return ProductGramsDto(
            product = productDtoMapper.mapFromDomainToDto(source.product),
            grams = source.grams
        )
    }

    fun mapFromDomainToDto(source: List<ProductGrams>): List<ProductGramsDto>{
        return source.map { mapFromDomainToDto(it) }
    }

    fun mapFromDtoToDomain(source: ProductGramsDto):ProductGrams{
        return ProductGrams(
            product = productDtoMapper.mapFromDtoToDomain(source.product),
            grams = source.grams
        )
    }

    fun mapFromDtoToDomain(source: List<ProductGramsDto>): List<ProductGrams>{
        return source.map { mapFromDtoToDomain(it) }
    }

}
