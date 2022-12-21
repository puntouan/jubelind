package com.jubel.jubelind.plannings.domain

import java.time.LocalDate

data class PlanningSummary(
    val start: LocalDate,
    val end: LocalDate,
    val calsPerWeek: Int,
    val proteinPerWeek: Int,
    val fatPerWeek: Int,
    val carbohydratesPerWeek: Int
)