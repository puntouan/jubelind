package com.jubel.jubelind.products.domain

import com.google.inject.ImplementedBy
import com.jubel.jubelind.products.infrastructure.ProductInMemoryRepository
import java.util.*

@ImplementedBy(ProductInMemoryRepository::class)
interface ProductRepository{

    fun createProduct(product: Product)
    fun getById(id: String): Optional<Product>
    fun deleteById(id: String)
    fun listAll(): List<Product>
    fun findByName(str: String): List<Product>
}