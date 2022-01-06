package com.jubel.jubelind

import com.jubel.jubelind.products.domain.ProductRepository

fun main(){
    val injector = JubelIND().start()

    val productRepository = injector.getInstance(ProductRepository::class.java)
    ProductsData().get().forEach {
        productRepository.createProduct(it)
    }
}

