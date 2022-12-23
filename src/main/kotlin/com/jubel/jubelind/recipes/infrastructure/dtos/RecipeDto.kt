package com.jubel.jubelind.recipes.infrastructure.dtos

import com.google.inject.Inject
import com.google.inject.Singleton
import com.jubel.jubelind.recipes.domain.Recipe
import com.jubel.jubelind.recipes.domain.RecipeId
import com.jubel.jubelind.shared.domain.pagination.Page
import com.jubel.jubelind.shared.infrastructure.dtos.PageDto
import com.jubel.jubelind.shared.infrastructure.dtos.PageInfoDtoMapper
import kotlinx.serialization.Serializable

@Serializable
data class RecipeDto(
    val id: String,
    val name: String,
    val quantityProducts: List<ProductGramsDto>,
){
    var description: String = ""

    init {
        description = quantityProducts.sortedBy { it.grams }.reversed().joinToString(", ") {
            it.product.name
        }
    }
}

@Singleton
class RecipeDtoMapper @Inject constructor(
    private val productGramsDtoMapper: ProductGramsDtoMapper
){

    fun mapFromDomainToDto(source: Recipe):RecipeDto{
        return RecipeDto(
            id = source.id.value,
            name = source.name,
            quantityProducts = productGramsDtoMapper.mapFromDomainToDto(source.quantityProducts)
        )
    }

    fun mapFromDomainToDto(source: List<Recipe>): List<RecipeDto>{
        return source.map { mapFromDomainToDto(it) }
    }

    fun mapFromDtoToDomain(source: RecipeDto): Recipe{
        return Recipe(
            id = RecipeId(source.id),
            name = source.name,
            quantityProducts = productGramsDtoMapper.mapFromDtoToDomain(source.quantityProducts)
        )
    }

    fun mapFromDtoToDomain(source: List<RecipeDto>): List<Recipe>{
        return source.map { mapFromDtoToDomain(it) }
    }

}

@Singleton
class RecipesPageDtoMapper @Inject constructor(
    private val pageInfoDtoMapper: PageInfoDtoMapper,
    private val recipeDtoMapper: RecipeDtoMapper
){

    fun mapFromDomainToDto(source: Page<Recipe>): PageDto<RecipeDto> {
        return PageDto(
            pageInfo = pageInfoDtoMapper.mapFromDomainToDto(source.pageInfo),
            records = recipeDtoMapper.mapFromDomainToDto(source.records)
        )
    }

    fun mapFromDtoToDomain(source: PageDto<RecipeDto>): Page<Recipe> {
        return Page(
            pageInfo = pageInfoDtoMapper.mapFromDtoToDomain(source.pageInfo),
            records = recipeDtoMapper.mapFromDtoToDomain(source.records)
        )
    }

}