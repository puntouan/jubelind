package com.jubel.jubelind.products.domain

import com.google.inject.ImplementedBy
import com.jubel.jubelind.products.infrastructure.SqliteProductRepository

//@ImplementedBy(ProductInMemoryRepository::class)
@ImplementedBy(SqliteProductRepository::class)
interface ProductRepository{

    fun createProduct(product: Product): Product
    fun getById(id: String): Product?
    fun validateAndGetById(id: String): Product{
        return getById(id) ?: throw Error("No such product with id: $id")
    }
    fun deleteById(id: String)
    fun deleteAll()
    fun listAll(): List<Product>
    fun findByName(str: String): List<Product>
    fun updateProduct(id: String, productData: ProductDataToUpdate): Product
}