package com.jubel.jubelind.recipes.infrastructure

import com.google.inject.Inject
import com.google.inject.Singleton
import com.jubel.jubelind.products.domain.Product
import com.jubel.jubelind.products.infrastructure.SqliteProductRepository
import com.jubel.jubelind.recipes.domain.ProductGrams
import java.sql.Connection
import java.sql.ResultSet

@Singleton
class SqliteQuantityProductRepository @Inject constructor(
    private val connection: Connection,
    private val productRepository: SqliteProductRepository
) {

    fun createQuantityProducts(recipeId: String, quantityProducts: List<ProductGrams>){
        if (quantityProducts.isEmpty()) return
        val query = "INSERT INTO quantity_product(recipeId, productId, grams) VALUES( ?, ?, ?)"
        val ps = connection.prepareStatement(query)
        quantityProducts.forEach { productGrams ->
            ps.setString(1, recipeId)
            ps.setString(2, productGrams.product.id.value)
            ps.setInt(3, productGrams.grams)
            ps.addBatch()
        }
        try {
            ps.executeBatch()
        }catch(ex: Exception){
            println("Exception when executing batch: $ps")
            throw ex
        }
    }

    fun setGrams(recipeId: String, productId: String, grams: Int) {
        val query = "UPDATE quantity_product SET grams = ? WHERE recipeId = ? AND productId = ?"

        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.setInt(1, grams)
        preparedStatement.setString(2, recipeId)
        preparedStatement.setString(3, productId)
        val ret = preparedStatement.executeUpdate()
        if (ret == 0){
            throw Error("No such quantity_product with recipeId: $recipeId and productId $productId")
        }
    }

    fun findByRecipeId(recipeId: String): List<ProductGrams> {
        val query = "SELECT * FROM quantity_product WHERE recipeId = ?"
        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.setString(1, recipeId)
        val rs = preparedStatement.executeQuery()
        val productIdsGrams = mutableListOf<Pair<String, Int>>()
        while (rs.next()){
            productIdsGrams.add(toProductIdGrams(rs))
        }
        val productIds = productIdsGrams.map { it.first }
        val productsByIds = productRepository.getByIds(productIds)
        return productIdsGrams.map { ProductGrams(
            product = productsByIds[it.first]!!,
            grams = it.second
        ) }
    }

    fun deleteQuantityProduct(recipeId: String, productId: String) {
        val query = "DELETE FROM quantity_product WHERE recipeId = ? AND productId = ?"
        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.setString(1, recipeId)
        preparedStatement.setString(2, productId)
        preparedStatement.executeUpdate()
    }

    fun addQuantityProduct(recipeId: String, productId: String, grams: Int) {
        val query = "INSERT INTO quantity_product(recipeId, productId, grams) VALUES( ?, ?, ?)"
        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.setString(1, recipeId)
        preparedStatement.setString(2, productId)
        preparedStatement.setInt(3, grams)
        preparedStatement.executeUpdate()
    }

    private fun toProductIdGrams(rs: ResultSet): Pair<String, Int>{
        val productId = rs.getString("productId")
        val grams = rs.getInt("grams")
        return Pair(productId, grams)
    }
}