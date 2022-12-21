package com.jubel.jubelind.shared.infrastructure.dtos

import com.google.inject.Singleton
import com.jubel.jubelind.shared.domain.pagination.PageInfo
import kotlinx.serialization.Serializable


@Serializable
data class PageInfoDto(
    val startOffset: Int?,
    val endOffset: Int?,
    val hasPreviousPage: Boolean,
    val hasNextPage: Boolean
)



@Singleton
class PageInfoDtoMapper{

    fun mapFromDomainToDto(source: PageInfo): PageInfoDto{
        return PageInfoDto(
            startOffset = source.startOffset,
            endOffset = source.endOffset,
            hasPreviousPage = source.hasPreviousPage,
            hasNextPage = source.hasNextPage
        )
    }

}
