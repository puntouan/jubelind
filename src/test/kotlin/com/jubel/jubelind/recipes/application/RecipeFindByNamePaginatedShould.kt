package com.jubel.jubelind.recipes.application

import com.jubel.jubelind.recipes.domain.RecipeMother
import com.jubel.jubelind.recipes.domain.RecipeRepository
import com.jubel.jubelind.shared.domain.pagination.Page
import com.jubel.jubelind.shared.domain.pagination.PageInfo
import com.jubel.jubelind.shared.domain.pagination.PaginationParams
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class RecipeFindByNamePaginatedShould {

    @Mock
    private lateinit var recipeRepository: RecipeRepository

    @Test
    fun `return the repository result`(){
        val repositoryResult = Page(
            PageInfo(0,9,false, true),
            RecipeMother.instances(3)
        )

        val paginationParams = PaginationParams(10)
        Mockito.`when`(recipeRepository.findByNamePaginated("searchKey", paginationParams)).thenReturn(repositoryResult)

        val resultRecipes = RecipeFindByNamePaginated(recipeRepository).run("searchKey", paginationParams)

        Assertions.assertEquals(repositoryResult, resultRecipes)
    }

}