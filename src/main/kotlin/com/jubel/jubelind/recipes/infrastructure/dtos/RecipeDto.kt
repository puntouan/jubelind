package com.jubel.jubelind.recipes.infrastructure.dtos

import com.jubel.jubelind.recipes.domain.Recipe
import com.jubel.jubelind.recipes.domain.RecipeId
import kotlinx.serialization.Serializable
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.factory.Mappers

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

@Mapper(uses = [ProductGramsDtoMapper::class])
abstract class RecipeDtoMapper{

    @Mapping(source = "id.value", target = "id")
    abstract fun mapFromDomainToDto(recipe: Recipe):RecipeDto

    @Mapping(source = "id", target = "id", qualifiedByName = ["toRecipeId"])
    abstract fun mapFromDtoToDomain(recipeDto: RecipeDto): Recipe

    @Named("toRecipeId")
    fun toRecipeId(id: String): RecipeId {
        return RecipeId(id)
    }

}

fun Recipe.mapFromDomainToDto(): RecipeDto =
    Mappers.getMapper(RecipeDtoMapper::class.java).mapFromDomainToDto(this)

fun RecipeDto.mapFromDtoToDomain(): Recipe =
    Mappers.getMapper(RecipeDtoMapper::class.java).mapFromDtoToDomain(this)