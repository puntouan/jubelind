package com.jubel.jubelind.plannings.infrastructure

import com.google.inject.Inject
import com.jubel.jubelind.plannings.application.PlanningSummarySearchPaginated
import com.jubel.jubelind.plannings.infrastructure.dtos.PlanningSummariesPageDtoMapper
import com.jubel.jubelind.shared.domain.pagination.PaginationParams
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import spark.kotlin.get

class PlanningSummariesPaginatedController @Inject constructor(
    private val planningSummarySearchPaginated: PlanningSummarySearchPaginated,
    private val planningSummariesPageDtoMapper: PlanningSummariesPageDtoMapper
) {

    init {
        get("/planning/summary/"){
            val first = request.queryParams("first")?.toInt()
            val last = request.queryParams("last")?.toInt()
            val after = request.queryParams("after")?.toInt()
            val before = request.queryParams("before")?.toInt()
            listPaginated(PaginationParams(first, last, after, before))
        }
    }

    private fun listPaginated(paginationParams: PaginationParams): Any{
        val page = planningSummarySearchPaginated.run(paginationParams)
        val pageDto = planningSummariesPageDtoMapper.mapFromDomainToDto(page)
        return Json.encodeToString(pageDto)
    }

}