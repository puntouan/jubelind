package com.jubel.jubelind.products.infrastructure.dtos

import com.jubel.jubelind.products.domain.ProductToCreate
import kotlinx.serialization.Serializable
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Serializable
class ProductToCreateDto(
    val name: String,
    val calories: Float,
    val protein: Float,
    val fat: Float,
    val carbohydrates: Float
)

@Mapper
abstract class ProductToCreateDtoMapper{

    abstract fun mapFromDtoToDomain(productToCreateDto: ProductToCreateDto):ProductToCreate

}

fun ProductToCreateDto.mapFromDtoToDomain(): ProductToCreate =
    Mappers.getMapper(ProductToCreateDtoMapper::class.java).mapFromDtoToDomain(this)