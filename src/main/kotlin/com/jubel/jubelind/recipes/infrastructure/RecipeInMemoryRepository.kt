package com.jubel.jubelind.recipes.infrastructure

import com.google.inject.Inject
import com.google.inject.Singleton
import com.jubel.jubelind.products.domain.Product
import com.jubel.jubelind.recipes.domain.*
import com.jubel.jubelind.shared.domain.pagination.Page
import com.jubel.jubelind.shared.domain.pagination.PaginationParams
import com.jubel.jubelind.shared.infrastructure.ListPaginator

@Singleton
class RecipeInMemoryRepository @Inject constructor(
    private val paginator: ListPaginator
): RecipeRepository {

    val recipes = mutableListOf<MutableRecipe>()

    override fun createRecipe(recipe: Recipe): Recipe {
        recipes.add(MutableRecipe(recipe))
        return recipe
    }

    override fun findByNamePaginated(name: String, paginationParams: PaginationParams): Page<Recipe> {
        val queryResult = (if (name.isBlank()) recipes else
            recipes.filter { it.name.contains(name, true) })
            .map { it.toRecipe() }.sortedBy { it.name }
        return paginator.getPage(queryResult, paginationParams)
    }

    override fun setGramsToProduct(recipeId: String, productId: String, grams: Int) {
        val recipe = recipes.firstOrNull { it.id == recipeId }
            ?: throw Error("No such recipe with id: $recipeId")
        val quantityProduct = recipe.quantityProducts.firstOrNull { it.product.id.value == productId }
            ?: throw Error("No such product with id: $productId")
        quantityProduct.grams = grams
    }

    override fun addQuantityProduct(recipeId: String, product: Product, grams: Int) {
        val recipe = recipes.firstOrNull { it.id == recipeId }
            ?: throw Error("No such recipe with id: $recipeId")
        if (recipe.quantityProducts.any{it.product.id.value == product.id.toString()}){
            throw Error("Duplicated product with id: ${product.id.value}")
        }
        recipe.quantityProducts.add(
            MutableProductGrams(product, grams)
        )
    }

    override fun deleteQuantityProduct(recipeId: String, productId: String) {
        recipes.firstOrNull { it.id == recipeId }
            ?.quantityProducts?.removeIf { it.product.id.value == productId }
    }

    override fun getById(recipeId: String): Recipe? {
        return recipes.firstOrNull { it.id == recipeId }?.toRecipe()
    }

    override fun setRecipeName(recipeId: String, name: String) {
        val recipe = recipes.firstOrNull { it.id == recipeId }
            ?: throw Error("No such recipe with id: $recipeId")
        recipe.name = name
    }

    data class MutableRecipe(
        var id: String,
        var name: String,
        var quantityProducts: MutableList<MutableProductGrams>
    ){
        constructor(recipe: Recipe): this(
            recipe.id.value,
            recipe.name,
            recipe.quantityProducts.map { MutableProductGrams(it) }.toMutableList()
        )

        fun toRecipe(): Recipe{
            return Recipe(
                RecipeId(id), name, quantityProducts.map { it.toProductGrams() }.sortedBy { it.grams }.reversed()
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