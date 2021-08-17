package com.jubel.jubelind.products.application

import com.google.inject.Inject
import com.jubel.jubelind.products.domain.Product
import com.jubel.jubelind.products.domain.ProductRepository
import java.util.*

class ProductGetById @Inject constructor(
    private val productRepository: ProductRepository
) {
    fun getById(id: String): Optional<Product> {
        println("Repo siendo getting:" + productRepository)
        return productRepository.getById(id)
    }
}