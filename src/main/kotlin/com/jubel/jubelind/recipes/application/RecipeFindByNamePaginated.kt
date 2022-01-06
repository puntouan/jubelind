package com.jubel.jubelind.recipes.application

import com.google.inject.Inject
import com.jubel.jubelind.recipes.domain.Recipe
import com.jubel.jubelind.recipes.domain.RecipeRepository
import com.jubel.jubelind.shared.domain.pagination.Page
import com.jubel.jubelind.shared.domain.pagination.PaginationParams

class RecipeFindByNamePaginated @Inject constructor(
    private val recipeRepository: RecipeRepository
) {
    fun run(name: String, paginationParams: PaginationParams): Page<Recipe> {
        return recipeRepository.findByNamePaginated(name, paginationParams)
    }
}