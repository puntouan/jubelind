package com.jubel.jubelind.recipes.infrastructure

import com.google.inject.Inject
import com.google.inject.Singleton
import com.jubel.jubelind.products.domain.Product
import com.jubel.jubelind.recipes.domain.ProductGrams
import com.jubel.jubelind.recipes.domain.Recipe
import com.jubel.jubelind.recipes.domain.RecipeId
import com.jubel.jubelind.recipes.domain.RecipeRepository
import com.jubel.jubelind.shared.domain.pagination.Page
import com.jubel.jubelind.shared.domain.pagination.PaginationParams
import com.jubel.jubelind.shared.infrastructure.PaginatorInMemoryRepository

@Singleton
class RecipeInMemoryRepository @Inject constructor(
    private val paginator: PaginatorInMemoryRepository
): RecipeRepository {

    private val recipes = mutableListOf<MutableRecipe>()

    override fun createRecipe(recipe: Recipe) {
        recipes.add(MutableRecipe(recipe))
    }

    override fun findByNamePaginated(name: String, paginationParams: PaginationParams): Page<Recipe> {
        val queryResult = recipes.filter { it.name.contains(name, true) }.map { it.toRecipe() }
        return paginator.getPage(queryResult, paginationParams)
    }

    data class MutableRecipe(
        var id: String,
        var name: String,
        var quantityProducts: List<MutableProductGrams>
    ){
        constructor(recipe: Recipe): this(
            recipe.id.value,
            recipe.name,
            recipe.quantityProducts.map { MutableProductGrams(it) }
        )

        fun toRecipe(): Recipe{
            return Recipe(
                RecipeId(id), name, quantityProducts.map { it.toProductGrams() }
            )
        }
    }

    data class MutableProductGrams(
        var product: Product,
        var grams: Int
    ){
        constructor(productGrams: ProductGrams) : this(
            productGrams.product,
            productGrams.grams
        )

        fun toProductGrams(): ProductGrams{
            return ProductGrams(
                product, grams
            )
        }
    }
}