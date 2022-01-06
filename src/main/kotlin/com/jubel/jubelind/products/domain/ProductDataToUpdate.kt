package com.jubel.jubelind.products.domain

import kotlinx.serialization.Serializable

@Serializable
data class ProductDataToUpdate(
    val name: String,
    val calories: Float,
    val protein: Float,
    val fat: Float,
    val carbohydrates: Float
)



