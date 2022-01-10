package com.jubel.jubelind.recipes.infrastructure

import com.google.inject.Inject
import com.jubel.jubelind.recipes.application.RecipeFindByNamePaginated
import com.jubel.jubelind.recipes.infrastructure.dtos.mapFromDomainToDto
import com.jubel.jubelind.shared.domain.pagination.PaginationParams
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import spark.kotlin.get

class RecipeFindByByNamePaginatedController @Inject constructor(
    private val recipeFindByNamePaginated: RecipeFindByNamePaginated
){

    init {
        get("/recipe/search/name"){
            val str = request.queryParams("key")
            val first = request.queryParams("first")?.toInt()
            val last = request.queryParams("last")?.toInt()
            val after = request.queryParams("after")?.toInt()
            val before = request.queryParams("before")?.toInt()
            findByNamePaginated(str, PaginationParams(first, last, after, before))
        }
    }

    fun findByNamePaginated(str: String, paginationParams: PaginationParams): Any{
        return Json.encodeToString(recipeFindByNamePaginated.run(str, paginationParams).mapFromDomainToDto())
    }

}