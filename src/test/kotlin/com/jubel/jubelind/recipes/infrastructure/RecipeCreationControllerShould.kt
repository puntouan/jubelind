package com.jubel.jubelind.recipes.infrastructure

import com.jubel.jubelind.recipes.application.RecipeCreation
import com.jubel.jubelind.recipes.domain.Recipe
import com.jubel.jubelind.recipes.domain.RecipeMother
import com.jubel.jubelind.recipes.domain.RecipeToCreate
import com.jubel.jubelind.recipes.domain.RecipeToCreateMother
import com.jubel.jubelind.recipes.infrastructure.dtos.RecipeDto
import com.jubel.jubelind.recipes.infrastructure.dtos.mapFromDtoToDomain
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import spark.Request

@ExtendWith(MockitoExtension::class)
class RecipeCreationControllerShould {

    private fun <T> kAny(type: Class<T>): T = Mockito.any(type)

    @Mock
    lateinit var request: Request

    @Mock
    lateinit var recipeCreation: RecipeCreation

    @Test
    fun `create a new recipe`(){
        //given
        val jsonRequest = Json.encodeToString(RecipeToCreateMother.instanceDto())
        Mockito.`when`(request.body()).thenReturn(jsonRequest)
        val expectedRecipe = RecipeMother.instance()
        Mockito.`when`(recipeCreation.run(kAny(RecipeToCreate::class.java))).thenReturn(expectedRecipe)

        // when
        val resultRecipe = Json.decodeFromString<RecipeDto>(RecipeCreationController(recipeCreation).createNewRecipe(request).toString()).mapFromDtoToDomain()

        // then
        Assertions.assertEquals(expectedRecipe, resultRecipe)
    }

}