package com.jubel.jubelind.products.infrastructure.dtos

import com.jubel.jubelind.products.domain.ProductDataToUpdate
import kotlinx.serialization.Serializable
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Serializable
class ProductDataToUpdateDto(
    val name: String,
    val calories: Float,
    val protein: Float,
    val fat: Float,
    val carbohydrates: Float
)

@Mapper
abstract class ProductDataToUpdateDtoMapper{

    abstract fun mapFromDtoToDomain(productData: ProductDataToUpdateDto):ProductDataToUpdate

}

fun ProductDataToUpdateDto.mapFromDtoToDomain(): ProductDataToUpdate =
    Mappers.getMapper(ProductDataToUpdateDtoMapper::class.java).mapFromDtoToDomain(this)