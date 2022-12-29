package com.jubel.jubelind.plannings.infrastructure.dtos

import com.google.inject.Inject
import com.google.inject.Singleton
import com.jubel.jubelind.plannings.domain.PlanningSummary
import com.jubel.jubelind.shared.domain.pagination.Page
import com.jubel.jubelind.shared.infrastructure.dtos.NumberFormatter
import com.jubel.jubelind.shared.infrastructure.dtos.PageDto
import com.jubel.jubelind.shared.infrastructure.dtos.PageInfoDtoMapper
import kotlinx.serialization.Serializable
import java.time.format.DateTimeFormatter

@Serializable
data class PlanningSummaryDto(
    val id: String,
    val start: String,
    val end: String,
    val calsPerWeek: Float,
    val proteinPerWeek: Float,
    val proteinCalsPerWeek: Float,
    val proteinCalsPercentage: Float,
    val fatPerWeek: Float,
    val fatCalsPerWeek: Float,
    val fatCalsPercentage: Float,
    val carbohydratesPerWeek: Float,
    val carbohydratesCalsPerWeek: Float,
    val carbohydratesCalsPercentage: Float
)


@Singleton
class PlanningSummaryDtoMapper @Inject constructor(
    private val formatter: NumberFormatter
){
    fun mapFromDomainToDto(source: PlanningSummary): PlanningSummaryDto{
        return PlanningSummaryDto(
            id = source.id,
            start = source.start.format(DateTimeFormatter.ISO_LOCAL_DATE),
            end = source.end.format(DateTimeFormatter.ISO_LOCAL_DATE),
            calsPerWeek = formatter.format(source.calsPerWeek),
            proteinPerWeek = formatter.format(source.proteinPerWeek),
            proteinCalsPerWeek = formatter.format(source.proteinCalsPerWeek),
            proteinCalsPercentage = formatter.format(source.proteinCalsPercentage),
            fatPerWeek = formatter.format(source.fatPerWeek),
            fatCalsPerWeek = formatter.format(source.fatCalsPerWeek),
            fatCalsPercentage = formatter.format(source.fatCalsPercentage),
            carbohydratesPerWeek = formatter.format(source.carbohydratesPerWeek),
            carbohydratesCalsPerWeek = formatter.format(source.carbohydratesCalsPerWeek),
            carbohydratesCalsPercentage = formatter.format(source.carbohydratesCalsPercentage)
        )
    }

    fun mapFromDomainToDto(source: List<PlanningSummary>): List<PlanningSummaryDto>{
        return source.map { mapFromDomainToDto(it) }
    }

}

@Singleton
class PlanningSummariesPageDtoMapper @Inject constructor(
    private val pageInfoDtoMapper: PageInfoDtoMapper,
    private val planningSummaryDtoMapper: PlanningSummaryDtoMapper
) {

    fun mapFromDomainToDto(source: Page<PlanningSummary>): PageDto<PlanningSummaryDto> {

        return PageDto(
            pageInfo = pageInfoDtoMapper.mapFromDomainToDto(source.pageInfo),
            records = planningSummaryDtoMapper.mapFromDomainToDto(source.records)
        )

    }

}