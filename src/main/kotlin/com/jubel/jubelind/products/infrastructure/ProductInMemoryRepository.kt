package com.jubel.jubelind.products.infrastructure

import com.google.inject.Singleton
import com.jubel.jubelind.products.domain.*
import java.util.*

@Singleton
class ProductInMemoryRepository: ProductRepository {

    val products = mutableListOf<MutableProduct>()

    override fun createProduct(product: Product) {
        products.add(MutableProduct(product))
    }

    override fun getById(id: String): Product? {
        return products.firstOrNull { it.id == id }?.toProduct()
    }

    override fun deleteById(id: String) {
        val removed = products.removeIf{it.id == id}
        if (!removed){
            throw NonExistingProductException()
        }
    }

    override fun listAll(): List<Product> {
        return products.map { it.toProduct() }.toMutableList()
    }

    override fun findByName(str: String): List<Product> {
        return products.filter { it.name.contains(str, true) }.map { it.toProduct() }
    }

    override fun updateProduct(id: String, productData: ProductDataToUpdate): Product {
        val mutableProduct = products.firstOrNull { it.id == id } ?: throw NonExistingProductException()
        mutableProduct.name = productData.name
        mutableProduct.calories = productData.calories
        mutableProduct.protein = productData.protein
        mutableProduct.fat = productData.fat
        mutableProduct.carbohydrates = productData.carbohydrates
        return mutableProduct.toProduct()
    }

    data class MutableProduct(
        val id: String,
        var name: String,
        var calories: Float,
        var protein: Float,
        var fat: Float,
        var carbohydrates: Float
    ){
        constructor(product: Product) : this(
            product.id.value,
            product.name,
            product.calories,
            product.protein,
            product.fat,
            product.carbohydrates
        )

        fun toProduct(): Product{
            return Product(
                ProductId(id),name, calories, protein, fat, carbohydrates
            )
        }

    }

}