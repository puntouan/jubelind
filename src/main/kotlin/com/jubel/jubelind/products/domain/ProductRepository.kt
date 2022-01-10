package com.jubel.jubelind.products.domain

import com.google.inject.ImplementedBy
import com.jubel.jubelind.products.infrastructure.ProductInMemoryRepository
import java.util.*

@ImplementedBy(ProductInMemoryRepository::class)
interface ProductRepository{

    fun createProduct(product: Product): Product
    fun getById(id: String): Product?
    fun deleteById(id: String)
    fun listAll(): List<Product>
    fun findByName(str: String): List<Product>
    fun updateProduct(id: String, productData: ProductDataToUpdate): Product
}