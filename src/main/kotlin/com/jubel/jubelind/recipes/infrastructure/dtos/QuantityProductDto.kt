package com.jubel.jubelind.recipes.infrastructure.dtos

import kotlinx.serialization.Serializable

@Serializable
data class QuantityProductDto (
    val productId: String,
    val grams: Int
)