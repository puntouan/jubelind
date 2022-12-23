package com.jubel.jubelind.plannings.infrastructure.dtos

import com.google.inject.Inject
import com.google.inject.Singleton
import com.jubel.jubelind.plannings.domain.PlanningSummary
import com.jubel.jubelind.shared.domain.pagination.Page
import com.jubel.jubelind.shared.infrastructure.dtos.PageDto
import com.jubel.jubelind.shared.infrastructure.dtos.PageInfoDtoMapper
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


@Singleton
class PlanningSummaryDtoMapper{

    fun mapFromDomainToDto(source: PlanningSummary): PlanningSummaryDto{
        return PlanningSummaryDto(
            start = source.start.toEpochDay(),
            end = source.end.toEpochDay(),
            calsPerWeek = source.calsPerWeek,
            proteinPerWeek = source.proteinPerWeek,
            fatPerWeek = source.fatPerWeek,
            carbohydratesPerWeek = source.carbohydratesPerWeek
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