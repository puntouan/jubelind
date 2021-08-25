package com.jubel.jubelind.products.infrastructure

import com.google.inject.Singleton
import com.jubel.jubelind.products.domain.Product
import com.jubel.jubelind.products.domain.ProductRepository
import com.jubel.jubelind.products.domain.NonExistingProductException
import java.util.*

@Singleton
class ProductInMemoryRepository: ProductRepository {

    val products = mutableListOf<Product>()

    override fun createProduct(product: Product) {
        products.add(product)
    }

    override fun getById(id: String): Optional<Product> {
        return products.firstOrNull { it.id.value == id }.let {
            if (it == null) Optional.empty() else Optional.of(it)
        }
    }

    override fun deleteById(id: String) {
        val removed = products.removeIf{it.id.value == id}
        if (!removed){
            throw NonExistingProductException()
        }
    }

    override fun listAll(): List<Product> {
        return products.toMutableList()
    }

    override fun findByName(str: String): List<Product> {
        return products.filter { it.name.contains(str, true) }
    }
}