package com.jubel.jubelind.shared.domain.pagination

class PaginationParams(
    val first: Int? = null,
    private val last: Int? = null,
    private val after: Int? = null,
    private val before: Int? = null
) {

    init {
        if (first == null && last == null) throw IllegalArgumentException("Either 'first' or 'last' is mandatory")
        if (first != null){
            if (first <= 0) throw IllegalArgumentException("Field 'first' must be greater than 0")
            if (after != null && after < 0) throw IllegalArgumentException("Field 'after' must be null, 0 or greater than 0")
            if (last != null) throw IllegalArgumentException("It is not possible to use the field 'first' and the field 'last' at the same time")
            if (before != null) throw IllegalArgumentException("It is not possible to use the field 'first' and the field 'before' at the same time")
        }else{
            if (last!! <= 0) throw IllegalArgumentException("Field 'last' must be greater than 0")
            if (before == null) throw IllegalArgumentException("Field 'before' is mandatory to use 'last'")
            if (before <= 0) throw IllegalArgumentException("Field 'before' must be greater then 0")
            if (after != null) throw IllegalArgumentException("It is not possible to use the field 'last' and the field 'after' at the same time")
        }
    }

    fun isForwardPagination(): Boolean{
        return first != null
    }

    fun getStartOffset(): Int{
        if (isForwardPagination()){
            if (after != null){
                return after + 1
            }
            return 0
        }
        val startOffset = before!! - last!!
        if (startOffset > 0){
            return startOffset
        }
        return 0
    }

    fun hasPreviousPage(): Boolean{
        return getStartOffset() > 0
    }

    fun getResultSize(): Int{
        if (isForwardPagination()) return first!! + 1
        if (getStartOffset() > 0) return last!!
        return before!!
    }

}