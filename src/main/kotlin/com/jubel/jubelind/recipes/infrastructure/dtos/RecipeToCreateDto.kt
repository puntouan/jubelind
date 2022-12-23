package com.jubel.jubelind.recipes.infrastructure.dtos

import com.google.inject.Inject
import com.google.inject.Singleton
import com.jubel.jubelind.recipes.domain.RecipeToCreate
import kotlinx.serialization.Serializable

@Serializable
data class RecipeToCreateDto(
    val name: String,
    val quantityProducts: List<ProductGramsDto>?
)

@Singleton
class RecipeToCreateDtoMapper @Inject constructor(
    private val productGramsDtoMapper: ProductGramsDtoMapper
){

    fun mapFromDtoToDomain(source: RecipeToCreateDto): RecipeToCreate{
        return RecipeToCreate(
            name = source.name,
            quantityProducts = source.quantityProducts?.let {
                productGramsDtoMapper.mapFromDtoToDomain(it)
            } ?: emptyList()
        )
    }


}
