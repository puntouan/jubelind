package com.jubel.jubelind.recipes.infrastructure.dtos

import com.jubel.jubelind.recipes.domain.Recipe
import kotlinx.serialization.Serializable
import kotlin.math.round

@Serializable
data class MacroDataDto(
    val protein: NutrientDataDto,
    val fat: NutrientDataDto,
    val carbohydrates: NutrientDataDto,
    val other: NutrientDataDto,
    val total: NutrientDataDto
)

@Serializable
data class NutrientDataDto(
    val grams: Float,
    val gramsPercentage: Float,
    val cals: Float,
    val calsPercentage: Float
)

class MacroDataDtoMapper{

    fun mapRecipeToMacroDataDto(recipe: Recipe): MacroDataDto{
        return MacroDataDto(
            protein = NutrientDataDto(
                grams = format(recipe.proteinGrams),
                gramsPercentage = format(recipe.proteinGramsPercentage),
                cals = format(recipe.proteinCals),
                calsPercentage = format(recipe.proteinCalsPercentage)
            ),
            fat = NutrientDataDto(
                grams = format(recipe.fatGrams),
                gramsPercentage = format(recipe.fatGramsPercentage),
                cals = format(recipe.fatCals),
                calsPercentage = format(recipe.fatCalsPercentage)
            ),
            carbohydrates = NutrientDataDto(
                grams = format(recipe.carbohydratesGrams),
                gramsPercentage = format(recipe.carbohydratesGramsPercentage),
                cals = format(recipe.carbohydratesCals),
                calsPercentage = format(recipe.carbohydratesCalsPercentage)
            ),
            other = NutrientDataDto(
                grams = format(0.0),
                gramsPercentage = format(0.0),
                cals = format(recipe.unknownCals),
                calsPercentage = format(recipe.unknownCalsPercentage)
            ),
            total = NutrientDataDto(
                grams = format(recipe.grams.toDouble()),
                gramsPercentage = format(100.0),
                cals = format(recipe.absolutCalories),
                calsPercentage = format(100.0)
            )
        )
    }

    private fun format(d: Double, decimals: Int = 2): Float{
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return (round(d * multiplier) / multiplier).toFloat()
    }
}

fun Recipe.mapFromDomainToMacroDataDto() = MacroDataDtoMapper().mapRecipeToMacroDataDto(this)