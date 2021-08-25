package com.jubel.jubelind.products.domain

class ProductSearching(
    val criteria: List<Criterion>
)

class Criterion(
    val field: String,
    val operator: String,
    val value: String
)