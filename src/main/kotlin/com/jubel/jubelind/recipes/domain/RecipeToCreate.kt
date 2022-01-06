package com.jubel.jubelind.recipes.domain

import kotlinx.serialization.Serializable

@Serializable
data class RecipeToCreate(
    val name: String
)