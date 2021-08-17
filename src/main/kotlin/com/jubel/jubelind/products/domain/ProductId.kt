package com.jubel.jubelind.products.domain

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class ProductId(val value: String = UUID.randomUUID().toString()){

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProductId

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

}