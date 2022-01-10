package com.jubel.jubelind.recipes.infrastructure.dtos

import com.jubel.jubelind.recipes.domain.ProductGrams
import com.jubel.jubelind.recipes.domain.RecipeToCreate
import kotlinx.serialization.Serializable
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.factory.Mappers

@Serializable
data class RecipeToCreateDto(
    val name: String,
    val quantityProducts: List<ProductGramsDto>?
)

@Mapper(uses = [ProductGramsDtoMapper::class])
abstract class RecipeToCreateDtoMapper{

    @Mapping(source = "quantityProducts", target = "quantityProducts", qualifiedByName = ["toProductGramsDomain"])
    abstract fun mapFromDtoToDomain(recipeToCreateDto: RecipeToCreateDto): RecipeToCreate

    @Named("toProductGramsDomain")
    fun toProductGramsDomain(quantityProducts: List<ProductGramsDto>?): List<ProductGrams> {
        return quantityProducts?.map { it.mapFromDtoToDomain() } ?: emptyList()
    }

}

fun RecipeToCreateDto.mapFromDtoToDomain(): RecipeToCreate =
    Mappers.getMapper(RecipeToCreateDtoMapper::class.java).mapFromDtoToDomain(this)