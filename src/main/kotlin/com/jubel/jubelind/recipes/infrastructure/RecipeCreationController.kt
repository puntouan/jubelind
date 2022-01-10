package com.jubel.jubelind.recipes.infrastructure

import com.google.inject.Inject
import com.jubel.jubelind.recipes.application.RecipeCreation
import com.jubel.jubelind.recipes.domain.RecipeToCreate
import com.jubel.jubelind.recipes.infrastructure.dtos.RecipeToCreateDto
import com.jubel.jubelind.recipes.infrastructure.dtos.mapFromDomainToDto
import com.jubel.jubelind.recipes.infrastructure.dtos.mapFromDtoToDomain
import com.jubel.jubelind.shared.infrastructure.BadRequestException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import spark.Request
import spark.kotlin.post

class RecipeCreationController @Inject constructor(
    private val recipeCreation: RecipeCreation
) {

    init {
        post("/recipe"){
            createNewRecipe(request)
        }
    }

    fun createNewRecipe(request: Request): Any{
        val recipeToCreate = try{
            Json.decodeFromString<RecipeToCreateDto>(request.body()).mapFromDtoToDomain()
        }catch (ex: SerializationException){
            throw BadRequestException("Failed to parse request. ${ex.message} ", ex)
        }
        val createdRecipe = recipeCreation.run(recipeToCreate)
        return Json.encodeToString(createdRecipe.mapFromDomainToDto())
    }

}