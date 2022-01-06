package com.jubel.jubelind.shared.domain.pagination

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

}

data class PageInfo(
    val startOffset: Int?,
    val endOffset: Int?,
    val hasPreviousPage: Boolean,
    val hasNextPage: Boolean
)