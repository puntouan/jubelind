package com.jubel.jubelind.recipes.infrastructure.dtos

import com.jubel.jubelind.recipes.domain.Recipe
import com.jubel.jubelind.shared.domain.pagination.Page
import com.jubel.jubelind.shared.infrastructure.dtos.PageInfoDto
import com.jubel.jubelind.shared.infrastructure.dtos.PageInfoDtoMapper
import kotlinx.serialization.Serializable
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Serializable
data class RecipesPageDto(
    val pageInfo: PageInfoDto,
    val records: List<RecipeDto>
)

@Mapper(uses = [PageInfoDtoMapper::class, RecipeDtoMapper::class])
abstract class RecipesPageDtoMapper{

    abstract fun mapFromDomainToDto(recipesPage: Page<Recipe>):RecipesPageDto

    abstract fun mapFromDtoToDomain(recipesPage: RecipesPageDto):Page<Recipe>

}

fun Page<Recipe>.mapFromDomainToDto(): RecipesPageDto =
    Mappers.getMapper(RecipesPageDtoMapper::class.java).mapFromDomainToDto(this)

fun RecipesPageDto.mapFromDtoToDomain(): Page<Recipe> =
    Mappers.getMapper(RecipesPageDtoMapper::class.java).mapFromDtoToDomain(this)