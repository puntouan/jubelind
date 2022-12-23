package com.jubel.jubelind.products.infrastructure.dtos

import com.google.inject.Singleton
import com.jubel.jubelind.products.domain.ProductToCreate
import kotlinx.serialization.Serializable

@Serializable
class ProductToCreateDto(
    val name: String,
    val calories: Float,
    val protein: Float,
    val fat: Float,
    val carbohydrates: Float
)

@Singleton
class ProductToCreateDtoMapper{

    fun mapFromDtoToDomain(source: ProductToCreateDto):ProductToCreate{
        return ProductToCreate(
            name = source.name,
            calories = source.calories,
            protein = source.protein,
            fat = source.fat,
            carbohydrates = source.carbohydrates
        )
    }

}
