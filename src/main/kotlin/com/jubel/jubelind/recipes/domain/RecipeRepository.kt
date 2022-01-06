package com.jubel.jubelind.recipes.domain

import com.google.inject.ImplementedBy
import com.jubel.jubelind.recipes.infrastructure.RecipeInMemoryRepository
import com.jubel.jubelind.shared.domain.pagination.Page
import com.jubel.jubelind.shared.domain.pagination.PaginationParams

@ImplementedBy(RecipeInMemoryRepository::class)
interface RecipeRepository {

    fun createRecipe(recipe: Recipe)
    fun findByNamePaginated(name: String, paginationParams: PaginationParams): Page<Recipe>

}