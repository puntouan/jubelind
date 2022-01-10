package com.jubel.jubelind.recipes.infrastructure.dtos

import com.jubel.jubelind.products.infrastructure.dtos.ProductDto
import com.jubel.jubelind.products.infrastructure.dtos.ProductDtoMapper
import com.jubel.jubelind.recipes.domain.ProductGrams
import kotlinx.serialization.Serializable
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Serializable
data class ProductGramsDto(
    val product: ProductDto,
    val grams: Int
)


@Mapper(uses = [ProductDtoMapper::class])
abstract class ProductGramsDtoMapper{

    abstract fun mapFromDomainToDto(productGrams: ProductGrams):ProductGramsDto

    abstract fun mapFromDtoToDomain(productGramsDto: ProductGramsDto):ProductGrams

}

fun ProductGrams.mapFromDomainToDto(): ProductGramsDto =
    Mappers.getMapper(ProductGramsDtoMapper::class.java).mapFromDomainToDto(this)

fun ProductGramsDto.mapFromDtoToDomain(): ProductGrams =
    Mappers.getMapper(ProductGramsDtoMapper::class.java).mapFromDtoToDomain(this)

fun List<ProductGrams>.mapFromDomainToDto(): List<ProductGramsDto> =
    this.map { it.mapFromDomainToDto()}

fun List<ProductGramsDto>.mapFromDtoToDomain(): List<ProductGrams> =
    this.map { it.mapFromDtoToDomain()}