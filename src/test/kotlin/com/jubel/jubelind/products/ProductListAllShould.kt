package com.jubel.jubelind.products

import com.google.inject.Injector
import com.jubel.jubelind.JubelIND
import com.jubel.jubelind.products.domain.ProductRepository
import com.jubel.jubelind.products.infrastructure.dtos.ProductDtoMapper
import com.jubel.jubelind.shared.SQLiteBase
import com.jubel.jubelind.shared.TestSQLiteModule
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import org.hamcrest.Matchers
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS

@TestInstance(PER_CLASS)
class ProductListAllShould: SQLiteBase() {

    private lateinit var jubelIND: JubelIND
    private lateinit var injector: Injector

    @BeforeAll
    fun setUp(){
        jubelIND = JubelIND()
        jubelIND.stopApp()
        injector = jubelIND.start(TestSQLiteModule())
    }

    @AfterAll
    fun stop(){
        jubelIND.stopApp()
    }

    @Test
    fun `return all existing products`() {

        Given {
            port(4567)
        } When {
            get("/product")
        } Then {
            statusCode(200)
            contentType(ContentType.JSON)
            val existingProducts = injector.getInstance(ProductRepository::class.java).listAll()
            val existingProductsDtos = ProductDtoMapper().mapFromDomainToDto(existingProducts.sortedBy { it.name })
            body(Matchers.equalTo(Json.encodeToJsonElement(existingProductsDtos).toString()))
        }

    }

}