package com.jubel.jubelind.recipes.infrastructure.dtos

import com.google.inject.Inject
import com.google.inject.Singleton
import com.jubel.jubelind.recipes.domain.Recipe
import com.jubel.jubelind.shared.infrastructure.dtos.NumberFormatter
import kotlinx.serialization.Serializable

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

@Singleton
class MacroDataDtoMapper @Inject constructor(
    private val formatter: NumberFormatter
){

    fun mapRecipeToMacroDataDto(recipe: Recipe): MacroDataDto{
        return MacroDataDto(
            protein = NutrientDataDto(
                grams = formatter.format(recipe.proteinGrams),
                gramsPercentage = formatter.format(recipe.proteinGramsPercentage),
                cals = formatter.format(recipe.proteinCals),
                calsPercentage = formatter.format(recipe.proteinCalsPercentage)
            ),
            fat = NutrientDataDto(
                grams = formatter.format(recipe.fatGrams),
                gramsPercentage = formatter.format(recipe.fatGramsPercentage),
                cals = formatter.format(recipe.fatCals),
                calsPercentage = formatter.format(recipe.fatCalsPercentage)
            ),
            carbohydrates = NutrientDataDto(
                grams = formatter.format(recipe.carbohydratesGrams),
                gramsPercentage = formatter.format(recipe.carbohydratesGramsPercentage),
                cals = formatter.format(recipe.carbohydratesCals),
                calsPercentage = formatter.format(recipe.carbohydratesCalsPercentage)
            ),
            other = NutrientDataDto(
                grams = formatter.format(0.0),
                gramsPercentage = formatter.format(0.0),
                cals = formatter.format(recipe.unknownCals),
                calsPercentage = formatter.format(recipe.unknownCalsPercentage)
            ),
            total = NutrientDataDto(
                grams = formatter.format(recipe.grams.toDouble()),
                gramsPercentage = formatter.format(100.0),
                cals = formatter.format(recipe.absolutCalories),
                calsPercentage = formatter.format(100.0)
            )
        )
    }

}
