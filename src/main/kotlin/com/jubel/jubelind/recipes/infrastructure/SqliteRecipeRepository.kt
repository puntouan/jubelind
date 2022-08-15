package com.jubel.jubelind.recipes.infrastructure

import com.google.inject.Inject
import com.google.inject.Singleton
import com.jubel.jubelind.products.domain.Product
import com.jubel.jubelind.recipes.domain.Recipe
import com.jubel.jubelind.recipes.domain.RecipeId
import com.jubel.jubelind.recipes.domain.RecipeRepository
import com.jubel.jubelind.shared.domain.pagination.Page
import com.jubel.jubelind.shared.domain.pagination.PaginationParams
import com.jubel.jubelind.shared.infrastructure.PaginatedQuery
import com.jubel.jubelind.shared.infrastructure.SqlitePaginator
import java.sql.Connection
import java.sql.ResultSet

@Singleton
class SqliteRecipeRepository @Inject constructor(
    private val connection: Connection,
    private val quantityProductRepository: SqliteQuantityProductRepository
) : RecipeRepository {

    override fun createRecipe(recipe: Recipe): Recipe {

        val query = "INSERT INTO recipes(id, name) VALUES( ?, ?)"
        val preparedStatement = connection.prepareStatement(query)

        connection.autoCommit = false

        preparedStatement.setString(1, recipe.id.value)
        preparedStatement.setString(2, recipe.name)
        preparedStatement.executeUpdate()
        quantityProductRepository.createQuantityProducts(recipe.id.value, recipe.quantityProducts)

        connection.commit()
        connection.autoCommit = true

        return recipe
    }

    override fun findByNamePaginated(name: String, paginationParams: PaginationParams): Page<Recipe> {

        val paginatedQuery = if (name.isBlank())
            PaginatedQuery("SELECT * FROM recipes")
        else {
            PaginatedQuery("SELECT * FROM recipes WHERE name LIKE ?").apply {
                setString(1,"%$name%")
            }
        }

        return SqlitePaginator().getPage(connection, paginatedQuery, paginationParams){
            toRecipe(it)
        }

    }

    override fun setGramsToProduct(recipeId: String, productId: String, grams: Int) {
        quantityProductRepository.setGrams(recipeId, productId, grams)
    }

    override fun getById(recipeId: String): Recipe? {
        val query = "SELECT * FROM recipes WHERE id = ?"
        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.setString(1, recipeId)
        preparedStatement.parameterMetaData
        val rs = preparedStatement.executeQuery()
        if (!rs.next()) return null
        return toRecipe(rs)
    }

    override fun deleteQuantityProduct(recipeId: String, productId: String) {
        quantityProductRepository.deleteQuantityProduct(recipeId, productId)
    }

    override fun setRecipeName(recipeId: String, name: String) {
        val query = "UPDATE recipes SET name = ? WHERE id = ?"

        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.setString(1, name)
        preparedStatement.setString(2, recipeId)
        val ret = preparedStatement.executeUpdate()
        if (ret == 0){
            throw Error("No such recipe with id: $recipeId")
        }
    }

    override fun addQuantityProduct(recipeId: String, product: Product, grams: Int) {
        quantityProductRepository.addQuantityProduct(recipeId, product.id.value, grams)
    }

    private fun toRecipe(rs: ResultSet): Recipe{
        val recipeId = rs.getString("id")
        val name = rs.getString("name")
        val productsGrams = quantityProductRepository.findByRecipeId(recipeId)
        return Recipe(
            id = RecipeId(recipeId),
            name = name,
            quantityProducts = productsGrams
        )
    }
}