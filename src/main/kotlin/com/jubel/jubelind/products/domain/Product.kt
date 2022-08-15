package com.jubel.jubelind.products.domain

class Product(
    val id: ProductId,
    val name: String,
    val calories: Float,
    val protein: Float,
    val fat: Float,
    val carbohydrates: Float
){
    private val gramsOfMacros = protein + fat + carbohydrates
    val unknownGrams = 100 - gramsOfMacros

    val caloriesPerGram = calories / 100.0

    val proteinPerGram = protein / 100.0
    val fatPerGram = fat / 100.0
    val carbohydratesPerGram = carbohydrates / 100.0

    val proteinCals = protein * CALS_PER_GRAM_OF_PROTEIN
    val fatCals = fat * CALS_PER_GRAM_OF_FAT
    val carbohydratesCals = carbohydrates * CALS_PER_GRAM_OF_CARBOHYDRATE

    private val calsOfMacros = proteinCals + fatCals + carbohydratesCals
    val unknownCals = calories - calsOfMacros
    val unknownCalsPercentage = if (calories == 0f) 0.0f else (unknownCals * 100) / calories

    val proteinCalsPercentage = if (calories == 0f) 0.0f else (proteinCals * 100) / calories
    val fatCalsPercentage = if (calories == 0f) 0.0f else (fatCals * 100) / calories
    val carbohydratesCalsPercentage = if (calories == 0f) 0.0f else (carbohydratesCals * 100) / calories

    companion object{

        const val CALS_PER_GRAM_OF_PROTEIN = 4
        const val CALS_PER_GRAM_OF_FAT = 9
        const val CALS_PER_GRAM_OF_CARBOHYDRATE = 4

        fun fromProductToCreate(productToCreate: ProductToCreate): Product{
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