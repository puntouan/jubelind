package com.jubel.jubelind.shared.infrastructure.dtos

import com.jubel.jubelind.shared.domain.pagination.PageInfo
import kotlinx.serialization.Serializable
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Serializable
data class PageInfoDto(
    val startOffset: Int?,
    val endOffset: Int?,
    val hasPreviousPage: Boolean,
    val hasNextPage: Boolean
)

@Mapper
abstract class PageInfoDtoMapper{

    abstract fun mapFromDomainToDto(pageInfo: PageInfo): PageInfoDto

}

fun PageInfo.mapFromDomainToDto(): PageInfoDto =
    Mappers.getMapper(PageInfoDtoMapper::class.java).mapFromDomainToDto(this)