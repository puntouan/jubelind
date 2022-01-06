package com.jubel.jubelind.recipes.domain

import com.jubel.jubelind.products.domain.Product
import com.jubel.jubelind.products.domain.ProductMother
import kotlin.random.Random

class ProductGramsMother {

    companion object {
        private val rnd = Random(System.currentTimeMillis())

        fun instance(
            product: Product = ProductMother.instance(),
            grams: Int = rnd.nextInt(500),
        ): ProductGrams {
            return ProductGrams(product, grams)
        }

        fun instances(n: Int): List<ProductGrams> {
            val productsGrams = mutableListOf<ProductGrams>()
            repeat(n) { productsGrams.add(ProductGramsMother.instance()) }
            return productsGrams
        }
    }

}