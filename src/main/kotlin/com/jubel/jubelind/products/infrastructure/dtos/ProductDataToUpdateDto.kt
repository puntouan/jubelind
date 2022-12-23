package com.jubel.jubelind.products.infrastructure.dtos

import com.google.inject.Singleton
import com.jubel.jubelind.products.domain.ProductDataToUpdate
import kotlinx.serialization.Serializable

@Serializable
class ProductDataToUpdateDto(
    val name: String,
    val calories: Float,
    val protein: Float,
    val fat: Float,
    val carbohydrates: Float
)


@Singleton
class ProductDataToUpdateDtoMapper{

    fun mapFromDtoToDomain(source: ProductDataToUpdateDto): ProductDataToUpdate {
        return ProductDataToUpdate(
            name = source.name,
            calories = source.calories,
            protein = source.protein,
            fat = source.fat,
            carbohydrates = source.carbohydrates
        )
    }

}