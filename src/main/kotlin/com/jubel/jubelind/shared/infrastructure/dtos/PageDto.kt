package com.jubel.jubelind.shared.infrastructure.dtos

import kotlinx.serialization.Serializable

@Serializable
data class PageDto<T>(
    val pageInfo: PageInfoDto,
    val records: List<T>
)
