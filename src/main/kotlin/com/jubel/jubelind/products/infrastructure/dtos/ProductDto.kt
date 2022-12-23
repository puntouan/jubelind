package com.jubel.jubelind.products.infrastructure.dtos

import com.google.inject.Singleton
import com.jubel.jubelind.products.domain.Product
import com.jubel.jubelind.products.domain.ProductId
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val id: String,
    val name: String,
    val calories: Float,
    val protein: Float,
    val fat: Float,
    val carbohydrates: Float
)

@Singleton
class ProductDtoMapper{

    fun mapFromDomainToDto(source: Product):ProductDto{
        return ProductDto(
            id = source.id.value,
            name = source.name,
            calories = source.calories,
            protein = source.protein,
            fat = source.fat,
            carbohydrates = source.carbohydrates
        )
    }

    fun mapFromDomainToDto(source: List<Product>):List<ProductDto>{
        return source.map { mapFromDomainToDto(it) }
    }

    fun mapFromDtoToDomain(source: ProductDto):Product{
        return Product(
            id = ProductId(source.id),
            name = source.name,
            calories = source.calories,
            protein = source.protein,
            fat = source.fat,
            carbohydrates = source.carbohydrates
        )
    }

    fun mapFromDtoToDomain(source: List<ProductDto>):List<Product>{
        return source.map { mapFromDtoToDomain(it) }
    }

}
