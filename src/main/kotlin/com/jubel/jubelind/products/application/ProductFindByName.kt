package com.jubel.jubelind.products.application

import com.google.inject.Inject
import com.jubel.jubelind.products.domain.Product
import com.jubel.jubelind.products.domain.ProductRepository

class ProductFindByName @Inject constructor(
    private val productRepository: ProductRepository
) {
    fun findByName(str: String): List<Product> {
        return productRepository.findByName(str)
    }
}