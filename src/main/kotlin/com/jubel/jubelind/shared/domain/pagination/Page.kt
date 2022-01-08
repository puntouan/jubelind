package com.jubel.jubelind.shared.domain.pagination

import kotlinx.serialization.Serializable

@Serializable
class Page<T>(
    val pageInfo: PageInfo,
    val records: List<T>
) {

    companion object{
        fun <T> buildEmpty(): Page<T> {
            return Page(
                PageInfo(null, null, false, false),
                emptyList()
            )
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Page<*>

        if (pageInfo != other.pageInfo) return false
        if (records != other.records) return false

        return true
    }

    override fun hashCode(): Int {
        var result = pageInfo.hashCode()
        result = 31 * result + records.hashCode()
        return result
    }

}

@Serializable
data class PageInfo(
    val startOffset: Int?,
    val endOffset: Int?,
    val hasPreviousPage: Boolean,
    val hasNextPage: Boolean
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PageInfo

        if (startOffset != other.startOffset) return false
        if (endOffset != other.endOffset) return false
        if (hasPreviousPage != other.hasPreviousPage) return false
        if (hasNextPage != other.hasNextPage) return false

        return true
    }

    override fun hashCode(): Int {
        var result = startOffset ?: 0
        result = 31 * result + (endOffset ?: 0)
        result = 31 * result + hasPreviousPage.hashCode()
        result = 31 * result + hasNextPage.hashCode()
        return result
    }
}