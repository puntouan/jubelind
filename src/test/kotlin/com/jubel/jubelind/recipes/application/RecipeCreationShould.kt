package com.jubel.jubelind.recipes.application

import com.jubel.jubelind.recipes.domain.Recipe
import com.jubel.jubelind.recipes.domain.RecipeRepository
import com.jubel.jubelind.recipes.domain.RecipeToCreateMother
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class RecipeCreationShould {

    private fun <T> kAny(type: Class<T>): T = Mockito.any(type)

    @Mock
    private lateinit var recipeRepository: RecipeRepository

    @Test
    fun `create a new recipe`() {
        val recipeToCreate = RecipeToCreateMother.instance()
        Mockito.`when`(recipeRepository.createRecipe(kAny(Recipe::class.java))).thenAnswer {it.arguments[0]}

        val recipeCreated = RecipeCreation(recipeRepository).run(recipeToCreate)

        Assertions.assertThat(recipeCreated.id).isNotNull
        Mockito.verify(recipeRepository).createRecipe(recipeCreated)
    }

}