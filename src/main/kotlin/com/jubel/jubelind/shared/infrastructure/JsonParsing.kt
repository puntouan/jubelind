package com.jubel.jubelind.shared.infrastructure

import com.jubel.jubelind.products.domain.Product
import com.jubel.jubelind.products.domain.ProductToCreate
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*

fun ProductToCreate.Companion.decodeFromString(source: String): ProductToCreate {
    return Json.decodeFromString(source)
}

fun Product.encodeToString(): String{
    val jsonObject = Json.encodeToJsonElement(this).jsonObject
    val jsonMap = jsonObject.toMutableMap()
    jsonMap.replace("id", JsonPrimitive(this.id.value))
    return Json.encodeToString(jsonMap)
}

fun List<Product>.encodeToString(): String{
    return "[${this.joinToString(","){it.encodeToString()}}]"
}

fun Message.encodeToString(): String{
    return Json.encodeToString(this)
}
