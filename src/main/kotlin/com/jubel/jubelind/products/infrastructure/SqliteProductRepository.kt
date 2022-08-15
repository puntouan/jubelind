package com.jubel.jubelind.products.infrastructure

import com.google.inject.Inject
import com.google.inject.Singleton
import com.jubel.jubelind.products.domain.*
import com.jubel.jubelind.shared.infrastructure.patientExecuteUpdate
import java.sql.Connection
import java.sql.ResultSet


@Singleton
class SqliteProductRepository @Inject constructor(
    private val connection: Connection
): ProductRepository {

    override fun createProduct(product: Product): Product {
        val query = "INSERT INTO products(id, name, calories, protein, fat, carbohydrates) VALUES( ?, ?, ?, ?, ?, ?)"

        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.setString(1, product.id.value)
        preparedStatement.setString(2, product.name)
        preparedStatement.setFloat(3, product.calories)
        preparedStatement.setFloat(4, product.protein)
        preparedStatement.setFloat(5, product.fat)
        preparedStatement.setFloat(6, product.carbohydrates)
        preparedStatement.patientExecuteUpdate()
        preparedStatement.close()
        return product
    }

    override fun getById(id: String): Product? {
        val query = "SELECT * FROM products WHERE id = ?"
        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.setString(1, id)
        val rs = preparedStatement.executeQuery()
        if (!rs.next()){
            preparedStatement.close()
            return null
        }
        val product = toProduct(rs)
        preparedStatement.close()
        return product
    }

    override fun deleteById(id: String) {
        val query = "DELETE FROM products WHERE id = ?"
        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.setString(1, id)
        val ret = preparedStatement.patientExecuteUpdate()
        preparedStatement.close()
        if (ret == 0){
            throw NonExistingProductException()
        }
    }

    override fun deleteAll() {
        val query = "DELETE FROM products"
        val preparedStatement = connection.prepareStatement(query)
        val ret = preparedStatement.patientExecuteUpdate()
        preparedStatement.close()
    }

    override fun listAll(): List<Product> {
        val query = "SELECT * FROM products"
        val preparedStatement = connection.prepareStatement(query)
        val rs = preparedStatement.executeQuery()
        val products = mutableListOf<Product>()
        while (rs.next()){
            products.add(toProduct(rs))
        }
        preparedStatement.close()
        return products
    }

    fun getByIds(productIds: List<String>): Map<String, Product> {
        if (productIds.isEmpty()) return emptyMap()
        val sqlProductIds = productIds.joinToString(",") { "'$it'" }
        val query = "SELECT * FROM products WHERE id IN ($sqlProductIds)"
        val preparedStatement = connection.createStatement()
        val rs = preparedStatement.executeQuery(query)
        val products = mutableMapOf<String, Product>()
        while (rs.next()){
            val product = toProduct(rs)
            products[product.id.value] = product
        }
        preparedStatement.close()
        return products
    }

    override fun findByName(str: String): List<Product> {
        return listAll().filter { it.name.contains(str, true) }
    }

    override fun updateProduct(id: String, productData: ProductDataToUpdate): Product {
        val query = "UPDATE products SET name = ?, calories = ?, protein = ?, fat = ?, carbohydrates = ? WHERE id = ?"

        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.setString(1, productData.name)
        preparedStatement.setFloat(2, productData.calories)
        preparedStatement.setFloat(3, productData.protein)
        preparedStatement.setFloat(4, productData.fat)
        preparedStatement.setFloat(5, productData.carbohydrates)
        preparedStatement.setString(6, id)
        val ret = preparedStatement.patientExecuteUpdate()
        preparedStatement.close()
        if (ret == 0){
            throw NonExistingProductException()
        }
        return getById(id)!!
    }

    private fun toProduct(rs: ResultSet): Product{
        val productId = rs.getString("id")
        val name = rs.getString("name")
        val calories = rs.getFloat("calories")
        val protein = rs.getFloat("protein")
        val fat = rs.getFloat("fat")
        val carbohydrates = rs.getFloat("carbohydrates")
        return Product(
            id = ProductId(productId),
            name = name,
            calories = calories,
            protein = protein,
            fat = fat,
            carbohydrates = carbohydrates
        )
    }
}