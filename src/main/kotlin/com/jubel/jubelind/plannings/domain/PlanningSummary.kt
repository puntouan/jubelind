package com.jubel.jubelind.plannings.domain

import com.jubel.jubelind.products.domain.Product
import java.time.LocalDate

data class PlanningSummary(
    val id: String,
    val start: LocalDate,
    val end: LocalDate,
    val calsPerWeek: Double,
    val proteinPerWeek: Double,
    val fatPerWeek: Double,
    val carbohydratesPerWeek: Double
){

    val proteinCalsPerWeek = proteinPerWeek * Product.CALS_PER_GRAM_OF_PROTEIN
    val proteinCalsPercentage = (proteinCalsPerWeek * 100) / calsPerWeek

    val fatCalsPerWeek = fatPerWeek * Product.CALS_PER_GRAM_OF_FAT
    val fatCalsPercentage = (fatCalsPerWeek * 100) / calsPerWeek

    val carbohydratesCalsPerWeek = carbohydratesPerWeek * Product.CALS_PER_GRAM_OF_CARBOHYDRATE
    val carbohydratesCalsPercentage = (carbohydratesCalsPerWeek * 100) / calsPerWeek

}