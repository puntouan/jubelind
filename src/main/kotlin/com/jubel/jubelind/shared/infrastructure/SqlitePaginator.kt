package com.jubel.jubelind.shared.infrastructure

import com.jubel.jubelind.products.domain.Product
import com.jubel.jubelind.shared.domain.pagination.Page
import com.jubel.jubelind.shared.domain.pagination.PageInfo
import com.jubel.jubelind.shared.domain.pagination.PaginationParams
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class SqlitePaginator {

    fun <T> getPage(connection: Connection, query: PaginatedQuery, paginationParams: PaginationParams, mapper: (rs: ResultSet) -> T ): Page<T> {

        val queryResult = executeQuery(connection, paginationParams.getStartOffset(), paginationParams.getResultSize(), query, mapper)
        if (paginationParams.isForwardPagination()) {
            return getForwardPage(queryResult, paginationParams)
        }
        return getBackwardPage(queryResult, paginationParams)

    }

    private fun  <T> getForwardPage(queryResult: List<T>, paginationParams: PaginationParams): Page<T> {

        val hasNextPage: Boolean = queryResult.size == paginationParams.getResultSize()
        val values = if (hasNextPage) queryResult.subList(0, (paginationParams.first!!)) else queryResult


        if (values.isEmpty()) return Page.buildEmpty()

        return Page(
            PageInfo(
                paginationParams.getStartOffset(),
                paginationParams.getStartOffset() + values.size - 1,
                paginationParams.hasPreviousPage(),
                hasNextPage),
            values
        )

    }

    private fun <T> getBackwardPage(queryResult: List<T>, paginationParams: PaginationParams): Page<T> {

        if (queryResult.isEmpty()) return Page.buildEmpty()

        return Page(
            PageInfo(
                paginationParams.getStartOffset(),
                paginationParams.getStartOffset() + queryResult.size - 1,
                paginationParams.hasPreviousPage(),
                true),
            queryResult
        )

    }

    private fun <T> executeQuery(connection: Connection, startOffset: Int, size: Int, query: PaginatedQuery, mapper: (rs: ResultSet) -> T ): List<T>  {
        val preparedStatement = connection.prepareStatement(query.query + " LIMIT ?, ?")
        query.applyParams(startOffset, size, preparedStatement)

        val rs = preparedStatement.executeQuery()
        val items = mutableListOf<T>()
        while (rs.next()){
            items.add(mapper(rs))
        }
        return items
    }

}

class PaginatedQuery(
    val query: String
){

    private val values = mutableListOf<QueryParam>()

    fun getParams(): List<QueryParam>{
        return values
    }

    fun setString(index: Int, str: String){
        values.add(QueryParam(index, QueryParamType.STRING, str))
    }

    fun setInt(index: Int, value: Int){
        values.add(QueryParam(index, QueryParamType.INT, value))
    }

    fun setLong(index: Int, value: Long){
        values.add(QueryParam(index, QueryParamType.LONG, value))
    }

    fun applyParams(startOffset: Int, size: Int, ps: PreparedStatement, ){
        setInt(values.size + 1, startOffset)
        setInt(values.size + 1, size)
        values.forEach {
            it.applyTo(ps)
        }
    }

}

class QueryParam(
    private val index: Int,
    private val type: QueryParamType,
    private val value: Any
){

    fun applyTo(ps: PreparedStatement){
        when(type){
            QueryParamType.STRING -> ps.setString(index, value as String)
            QueryParamType.INT -> ps.setInt(index, value as Int)
            QueryParamType.LONG -> ps.setLong(index, value as Long)
        }
    }

}

enum class QueryParamType{
    STRING, INT, LONG
}