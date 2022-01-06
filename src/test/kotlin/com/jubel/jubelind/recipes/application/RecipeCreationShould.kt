package com.jubel.jubelind.recipes.application

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

    @Mock
    private lateinit var recipeRepository: RecipeRepository

    @Test
    fun `create a new product`() {
        val newRecipe = RecipeToCreateMother.instance()

        val recipeCreated = RecipeCreation(recipeRepository).run(newRecipe)

        Assertions.assertThat(recipeCreated.id).isNotNull
        Mockito.verify(recipeRepository).createRecipe(recipeCreated)
    }

}