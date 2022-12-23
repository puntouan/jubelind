package com.jubel.jubelind.recipes.infrastructure

import com.google.inject.Inject
import com.jubel.jubelind.recipes.application.RecipeCreation
import com.jubel.jubelind.recipes.infrastructure.dtos.RecipeDtoMapper
import com.jubel.jubelind.recipes.infrastructure.dtos.RecipeToCreateDtoMapper
import com.jubel.jubelind.shared.infrastructure.BadRequestException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import spark.Request
import spark.kotlin.post

class RecipeCreationController @Inject constructor(
    private val recipeCreation: RecipeCreation,
    private val recipeToCreateDtoMapper : RecipeToCreateDtoMapper,
    private val recipeDtoMapper: RecipeDtoMapper
) {

    init {
        post("/recipe"){
            createNewRecipe(request)
        }
    }

    fun createNewRecipe(request: Request): Any{
        val recipeToCreate = try{
            recipeToCreateDtoMapper.mapFromDtoToDomain(
                Json.decodeFromString(request.body())
            )
        }catch (ex: SerializationException){
            throw BadRequestException("Failed to parse request. ${ex.message} ", ex)
        }
        val createdRecipe = recipeCreation.run(recipeToCreate)
        return Json.encodeToString(
            recipeDtoMapper.mapFromDomainToDto(createdRecipe)
        )
    }

}