package com.jubel.jubelind.recipes.infrastructure

import com.google.inject.Inject
import com.jubel.jubelind.recipes.application.GetRecipeById
import com.jubel.jubelind.recipes.application.RecipeCreation
import com.jubel.jubelind.recipes.domain.RecipeToCreate
import com.jubel.jubelind.recipes.infrastructure.dtos.RecipeToCreateDto
import com.jubel.jubelind.recipes.infrastructure.dtos.mapFromDomainToDto
import com.jubel.jubelind.recipes.infrastructure.dtos.mapFromDtoToDomain
import com.jubel.jubelind.shared.infrastructure.BadRequestException
import com.jubel.jubelind.shared.infrastructure.NotFoundException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import spark.Request
import spark.kotlin.get
import spark.kotlin.post

class GetRecipeByIdController @Inject constructor(
    private val getRecipeById: GetRecipeById
) {

    init {
        get("/recipe/:recipeId"){
            val recipeId: String = request.params(":recipeId")
            getRecipeById(recipeId)
        }
    }

    fun getRecipeById(recipeId: String): Any{
        val recipe = try{
            getRecipeById.run(recipeId)
        }catch (ex: SerializationException){
            throw NotFoundException("No suche recipe with id: $recipeId")
        }
        return Json.encodeToString(recipe.mapFromDomainToDto())
    }

}