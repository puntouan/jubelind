package com.jubel.jubelind.recipes.domain

import com.jubel.jubelind.products.infrastructure.dtos.ProductDtoMapper
import com.jubel.jubelind.recipes.infrastructure.dtos.ProductGramsDtoMapper
import com.jubel.jubelind.recipes.infrastructure.dtos.RecipeToCreateDto
import com.jubel.jubelind.recipes.infrastructure.dtos.RecipeToCreateDtoMapper
import kotlin.random.Random

class RecipeToCreateMother {

    companion object{

        private val rnd = Random(System.currentTimeMillis())

        private val recipeToCreateDtoMapper = RecipeToCreateDtoMapper(
            ProductGramsDtoMapper(ProductDtoMapper())
        )

        fun instanceDto(
            name: String = "Name - ${System.currentTimeMillis()}"
        ): RecipeToCreateDto {
            return RecipeToCreateDto(name, emptyList())
        }

        fun instance(
            name: String = "Name - ${System.currentTimeMillis()}"
        ): RecipeToCreate {
            return recipeToCreateDtoMapper.mapFromDtoToDomain(instanceDto(name))
        }
    }

}