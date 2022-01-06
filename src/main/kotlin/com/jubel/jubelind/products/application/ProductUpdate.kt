package com.jubel.jubelind.products.application

import com.google.inject.Inject
import com.jubel.jubelind.products.domain.Product
import com.jubel.jubelind.products.domain.ProductDataToUpdate
import com.jubel.jubelind.products.domain.ProductRepository

class ProductUpdate @Inject constructor(
    private val productRepository: ProductRepository
) {

    fun update(id: String, productData: ProductDataToUpdate): Product {
       return  productRepository.updateProduct(id, productData)
    }

}