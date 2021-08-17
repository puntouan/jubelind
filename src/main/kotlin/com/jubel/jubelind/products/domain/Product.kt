package com.jubel.jubelind.products.domain

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: ProductId,
    val name: String,
    val calories: Float,
    val protein: Float,
    val fat: Float,
    val carbohydrates: Float
){

    companion object{
        fun fromProductForCreate(productToCreate: ProductToCreate): Product{
            return Product(
                ProductId(),
                productToCreate.name,
                productToCreate.calories,
                productToCreate.protein,
                productToCreate.fat,
                productToCreate.carbohydrates
            )
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Product

        if (id != other.id) return false
        if (name != other.name) return false
        if (calories != other.calories) return false
        if (protein != other.protein) return false
        if (fat != other.fat) return false
        if (carbohydrates != other.carbohydrates) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + calories.hashCode()
        result = 31 * result + protein.hashCode()
        result = 31 * result + fat.hashCode()
        result = 31 * result + carbohydrates.hashCode()
        return result
    }


}