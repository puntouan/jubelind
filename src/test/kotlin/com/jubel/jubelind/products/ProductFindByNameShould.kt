package com.jubel.jubelind.products

import com.google.inject.Injector
import com.jubel.jubelind.JubelIND
import com.jubel.jubelind.products.domain.Product
import com.jubel.jubelind.products.domain.ProductMother
import com.jubel.jubelind.products.domain.ProductRepository
import com.jubel.jubelind.products.infrastructure.dtos.ProductDtoMapper
import com.jubel.jubelind.shared.SQLiteBase
import com.jubel.jubelind.shared.TestSQLiteModule
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.hamcrest.Matchers
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS

@TestInstance(PER_CLASS)
class ProductFindByNameShould: SQLiteBase() {

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
    fun `return all existing products filtering by key`() {
        val existingProducts = listOf(
            ProductMother.instance(name = "-test-Manzana"),
            ProductMother.instance(name = "-test-Mandarina"),
            ProductMother.instance(name = "-test-Mantequilla"),
            ProductMother.instance(name = "-test-Mangrana"),
            ProductMother.instance(name = "-test-Carne de ternera"),
            ProductMother.instance(name = "-test-Solomillo de cerdo"),
            ProductMother.instance(name = "-test-Pistachos")
        )

        val key = "-test-"
        Given {
            repositoryWith(existingProducts)
            port(4567)
            queryParam("key",key)
        } When {
            get("/product/search/name")
        } Then {
            statusCode(200)
            contentType(ContentType.JSON)
            val expectedProducts = existingProducts.filter { it.name.contains(key, true) }.sortedBy { it.name }
            body(Matchers.equalTo(Json.encodeToString(ProductDtoMapper().mapFromDomainToDto(expectedProducts))))
        }
    }

    private fun repositoryWith(existingProducts: List<Product>) {
        val productRepository = injector.getInstance(ProductRepository::class.java)
        existingProducts.forEach {
            productRepository.createProduct(it)
        }
    }

}