package com.jubel.jubelind.recipes.domain

import com.jubel.jubelind.recipes.infrastructure.dtos.RecipeToCreateDto
import com.jubel.jubelind.recipes.infrastructure.dtos.mapFromDtoToDomain
import kotlin.random.Random

class RecipeToCreateMother {

    companion object{

        private val rnd = Random(System.currentTimeMillis())

        fun instanceDto(
            name: String = "Name - ${System.currentTimeMillis()}"
        ): RecipeToCreateDto {
            return RecipeToCreateDto(name, emptyList())
        }

        fun instance(
            name: String = "Name - ${System.currentTimeMillis()}"
        ): RecipeToCreate {
            return instanceDto(name).mapFromDtoToDomain()
        }
    }

}