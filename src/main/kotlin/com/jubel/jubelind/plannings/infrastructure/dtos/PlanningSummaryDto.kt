package com.jubel.jubelind.plannings.infrastructure.dtos

import kotlinx.serialization.Serializable

@Serializable
data class PlanningSummaryDto(
    val start: Long,
    val end: Long,
    val calsPerWeek: Int,
    val proteinPerWeek: Int,
    val fatPerWeek: Int,
    val carbohydratesPerWeek: Int
)
