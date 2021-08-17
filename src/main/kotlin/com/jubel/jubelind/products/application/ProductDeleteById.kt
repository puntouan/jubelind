package com.jubel.jubelind.products.application

import com.google.inject.Inject
import com.jubel.jubelind.products.domain.ProductRepository

class ProductDeleteById @Inject constructor(
    private val productRepository: ProductRepository
) {
    fun deleteById(productId: String) {
        println("Repo siendo borrado:" + productRepository)
        productRepository.deleteById(productId)
    }

}
