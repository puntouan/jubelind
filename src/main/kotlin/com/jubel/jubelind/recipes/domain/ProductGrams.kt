package com.jubel.jubelind.recipes.domain

import com.jubel.jubelind.products.domain.Product

class ProductGrams(
    val product: Product,
    val grams: Int
) {



    val absolutCalories = grams * product.caloriesPerGram
    val proteinGrams = grams * product.proteinPerGram
    val proteinCals = proteinGrams * Product.CALS_PER_GRAM_OF_PROTEIN
    val fatGrams = grams * product.fatPerGram
    val fatCals = fatGrams * Product.CALS_PER_GRAM_OF_FAT
    val carbohydratesGrams = grams * product.carbohydratesPerGram
    val carbohydratesCals = carbohydratesGrams * Product.CALS_PER_GRAM_OF_CARBOHYDRATE
    val calsOfMacros = proteinCals + fatCals + carbohydratesCals

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProductGrams

        if (product != other.product) return false
        if (grams != other.grams) return false

        return true
    }

    override fun hashCode(): Int {
        var result = product.hashCode()
        result = 31 * result + grams
        return result
    }
}