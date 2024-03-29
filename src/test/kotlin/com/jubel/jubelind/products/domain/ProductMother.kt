package com.jubel.jubelind.products.domain

import kotlinx.serialization.Serializable
import java.util.*
import kotlin.math.roundToInt
import kotlin.random.Random

class ProductMother {

    companion object{
        private val rnd = Random(System.currentTimeMillis())

        fun instance(
            id: String =UUID.randomUUID().toString(),
            name: String = "Name - ${UUID.randomUUID()}",
            calories: Float = (rnd.nextFloat() * 100).roundToInt() / 100f,
            protein: Float = (rnd.nextFloat() * 100).roundToInt() / 100f,
            fat: Float = (rnd.nextFloat() * 100).roundToInt() / 100f,
            carbohydrates: Float = (rnd.nextFloat() * 100).roundToInt() / 100f,
        ): Product{
            return Product(ProductId(id), name, calories, protein, fat, carbohydrates)
        }

        fun instances(n: Int): List<Product>{
            val products = mutableListOf<Product>()
            repeat(n){products.add(instance())}
            return products
        }

        private fun fromProductJson2Product(productJson: ProductJson): Product{
            return Product(
                ProductId(productJson.id),
                productJson.name,
                productJson.calories,
                productJson.protein,
                productJson.fat,
                productJson.carbohydrates)
        }
    }


    @Serializable
    data class ProductJson(
        val id: String,
        val name: String,
        val calories: Float,
        val protein: Float,
        val fat: Float,
        val carbohydrates: Float
    )

}