package com.jubel.jubelind.recipes.domain

import com.google.inject.ImplementedBy
import com.jubel.jubelind.products.domain.Product
import com.jubel.jubelind.recipes.infrastructure.SqliteRecipeRepository
import com.jubel.jubelind.shared.domain.pagination.Page
import com.jubel.jubelind.shared.domain.pagination.PaginationParams

//@ImplementedBy(RecipeInMemoryRepository::class)
@ImplementedBy(SqliteRecipeRepository::class)
interface RecipeRepository {

    fun createRecipe(recipe: Recipe): Recipe
    fun findByNamePaginated(name: String, paginationParams: PaginationParams): Page<Recipe>
    fun setGramsToProduct(recipeId: String, productId: String, grams: Int)
    fun getById(recipeId: String): Recipe?
    fun deleteQuantityProduct(recipeId: String, productId: String)
    fun setRecipeName(recipeId: String, name: String)
    fun addQuantityProduct(recipeId: String, product: Product, grams: Int)
}