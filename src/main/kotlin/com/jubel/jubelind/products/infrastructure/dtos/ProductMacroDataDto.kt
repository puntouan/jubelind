package com.jubel.jubelind.products.infrastructure.dtos

import com.jubel.jubelind.products.domain.Product
import kotlinx.serialization.Serializable
import kotlin.math.round

@Serializable
data class ProductMacroDataDto(
    val protein: ProductNutrientDataDto,
    val fat: ProductNutrientDataDto,
    val carbohydrates: ProductNutrientDataDto,
    val other: ProductNutrientDataDto,
    val total: ProductNutrientDataDto
)

@Serializable
data class ProductNutrientDataDto(
    val grams: Float,
    val cals: Float,
    val calsPercentage: Float,
)

class ProductMacroDataDtoMapper{

    fun mapProductToMacroDataDto(product: Product): ProductMacroDataDto {
        return ProductMacroDataDto(
            protein = ProductNutrientDataDto(
                grams = format(product.protein),
                cals = format(product.proteinCals),
                calsPercentage = format(product.proteinCalsPercentage)
            ),
            fat = ProductNutrientDataDto(
                grams = format(product.fat),
                cals = format(product.fatCals),
                calsPercentage = format(product.fatCalsPercentage)
            ),
            carbohydrates = ProductNutrientDataDto(
                grams = format(product.carbohydrates),
                cals = format(product.carbohydratesCals),
                calsPercentage = format(product.carbohydratesCalsPercentage)
            ),
            other = ProductNutrientDataDto(
                grams = format(product.unknownGrams),
                cals = format(product.unknownCals),
                calsPercentage = format(product.unknownCalsPercentage)
            ),
            total = ProductNutrientDataDto(
                grams = format(100.0),
                cals = format(product.calories),
                calsPercentage = format(100.0)
            )
        )
    }

    private fun format(f: Float, decimals: Int = 2): Float{
        return format(f.toDouble(), decimals)
    }

    private fun format(d: Double, decimals: Int = 2): Float{
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return (round(d * multiplier) / multiplier).toFloat()
    }
}

fun Product.mapFromDomainToMacroDataDto() = ProductMacroDataDtoMapper().mapProductToMacroDataDto(this)