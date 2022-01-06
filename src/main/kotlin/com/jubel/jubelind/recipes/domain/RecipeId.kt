package com.jubel.jubelind.recipes.domain

import com.jubel.jubelind.products.domain.ProductId
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class RecipeId(val value: String = UUID.randomUUID().toString()) {

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