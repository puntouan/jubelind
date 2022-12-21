package com.jubel.jubelind.shared.infrastructure

import com.jubel.jubelind.shared.domain.pagination.Page
import com.jubel.jubelind.shared.domain.pagination.PageInfo
import com.jubel.jubelind.shared.domain.pagination.PaginationParams

class ListPaginator {

    fun <T> getPage(records: List<T>, paginationParams: PaginationParams): Page<T>{

        if (paginationParams.isForwardPagination()) {
            return getForwardPage(records, paginationParams)
        }
        return getBackwardPage(records, paginationParams)

    }

    private fun <T> getFrame(records: List<T>, start:Int, size: Int): List<T>{
        if (start >= records.size) return emptyList()
        if (start + size > records.size) return records.subList(start, records.size)
        return records.subList(start, start + size)
    }

    private fun <T> getForwardPage(records: List<T>, pagParams: PaginationParams): Page<T>  {

        val frame = getFrame(records, pagParams.getStartOffset(), pagParams.getResultSize())

        val hasNextPage = frame.size == pagParams.getResultSize()
        val values = if (hasNextPage) frame.subList(0, pagParams.first!!) else frame

        if (values.isEmpty()) return Page.buildEmpty()

        return Page(
            PageInfo(
                   pagParams.getStartOffset(),
                   pagParams.getStartOffset() + values.size - 1,
                pagParams.hasPreviousPage(),
                hasNextPage),
            values
        )

    }

    private fun <T> getBackwardPage(records: List<T>, pagParams: PaginationParams): Page<T> {

        val frame = getFrame(records, pagParams.getStartOffset(), pagParams.getResultSize())

        if (frame.isEmpty()) return Page.buildEmpty()

        return Page(
                PageInfo(
                    pagParams.getStartOffset(),
                    pagParams.getStartOffset() + frame.size - 1,
                    pagParams.hasPreviousPage(),
                     true),
                frame
        )

    }

}