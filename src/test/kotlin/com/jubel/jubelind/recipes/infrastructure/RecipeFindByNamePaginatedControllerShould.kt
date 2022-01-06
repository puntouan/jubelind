package com.jubel.jubelind.recipes.infrastructure

import com.jubel.jubelind.recipes.application.RecipeFindByNamePaginated
import com.jubel.jubelind.recipes.domain.Recipe
import com.jubel.jubelind.recipes.domain.RecipeMother
import com.jubel.jubelind.shared.domain.pagination.Page
import com.jubel.jubelind.shared.domain.pagination.PageInfo
import com.jubel.jubelind.shared.domain.pagination.PaginationParams
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class RecipeFindByNamePaginatedControllerShould {

    @Mock
    lateinit var recipeFindByNamePaginated: RecipeFindByNamePaginated

    private val recipeFindByByNamePaginatedController = RecipeFindByByNamePaginatedController(recipeFindByNamePaginated)

    @Test
    fun `return matching products`(){

        // given
        val matchingRecipes = RecipeMother.instances(5)
        val paginationParams = PaginationParams(10)
        val pageMatchingRecipes = Page(
            PageInfo(0,9,false,true),
            matchingRecipes
        )

        Mockito.`when`(recipeFindByNamePaginated.run("str", paginationParams))
            .thenReturn(pageMatchingRecipes)

        // when
        val result = Json.decodeFromString<Page<Recipe>>(
            recipeFindByByNamePaginatedController.findByNamePaginated("str", paginationParams).toString()
        )

        // then
        Assertions.assertEquals(pageMatchingRecipes, result)
    }

}