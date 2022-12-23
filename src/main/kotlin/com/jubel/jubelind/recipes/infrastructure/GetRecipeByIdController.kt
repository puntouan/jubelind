package com.jubel.jubelind.recipes.infrastructure

import com.google.inject.Inject
import com.jubel.jubelind.recipes.application.GetRecipeById
import com.jubel.jubelind.recipes.infrastructure.dtos.RecipeDtoMapper
import com.jubel.jubelind.shared.infrastructure.NotFoundException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import spark.kotlin.get

class GetRecipeByIdController @Inject constructor(
    private val getRecipeById: GetRecipeById,
    private val recipeDtoMapper: RecipeDtoMapper
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
        return Json.encodeToString(
            recipeDtoMapper.mapFromDomainToDto(recipe)
        )
    }

}