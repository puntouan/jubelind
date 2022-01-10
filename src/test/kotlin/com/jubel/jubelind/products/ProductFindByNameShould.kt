package com.jubel.jubelind.products

import com.google.inject.Injector
import com.jubel.jubelind.JubelIND
import com.jubel.jubelind.products.domain.Product
import com.jubel.jubelind.products.domain.ProductMother
import com.jubel.jubelind.products.domain.ProductRepository
import com.jubel.jubelind.products.infrastructure.dtos.mapFromDomainToDto
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
class ProductFindByNameShould {

    private lateinit var jubelIND: JubelIND
    private lateinit var injector: Injector

    @BeforeAll
    fun setUp(){
        jubelIND = JubelIND()
        injector = jubelIND.start()
    }

    @AfterAll
    fun stop(){
        jubelIND.stopApp()
    }

    @Test
    fun `return all existing products`() {
        val existingProducts = listOf(
            ProductMother.instance(name = "Manzana"),
            ProductMother.instance(name = "Mandarina"),
            ProductMother.instance(name = "Mantequilla"),
            ProductMother.instance(name = "Mangrana"),
            ProductMother.instance(name = "Carne de ternera"),
            ProductMother.instance(name = "Solomillo de cerdo"),
            ProductMother.instance(name = "Pistachos")
        )

        val key = "man"
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
            body(Matchers.equalTo(Json.encodeToString(expectedProducts.mapFromDomainToDto())))
        }
    }

    private fun repositoryWith(existingProducts: List<Product>) {
        val productRepository = injector.getInstance(ProductRepository::class.java)
        existingProducts.forEach {
            productRepository.createProduct(it)
        }
    }

}