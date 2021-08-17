package com.jubel.jubelind.products.application

import com.google.inject.Inject
import com.jubel.jubelind.products.domain.Product
import com.jubel.jubelind.products.domain.ProductToCreate
import com.jubel.jubelind.products.domain.ProductRepository

class ProductCreation @Inject constructor(
    private val productRepository: ProductRepository
) {

    fun create(productToCreate: ProductToCreate): Product {
        val product = Product.fromProductForCreate(productToCreate)
        productRepository.createProduct(product)
        return product
    }

}