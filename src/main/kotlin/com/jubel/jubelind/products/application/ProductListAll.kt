package com.jubel.jubelind.products.application

import com.google.inject.Inject
import com.jubel.jubelind.products.domain.Product
import com.jubel.jubelind.products.domain.ProductRepository

class ProductListAll @Inject constructor(
    private val productRepository: ProductRepository
) {
    fun listAll(): List<Product> {
        return productRepository.listAll().sortedBy { it.name }
    }
}