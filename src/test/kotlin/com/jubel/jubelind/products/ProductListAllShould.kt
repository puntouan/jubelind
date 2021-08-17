package com.jubel.jubelind.products

import com.google.inject.Injector
import com.jubel.jubelind.JubelIND
import com.jubel.jubelind.products.domain.Product
import com.jubel.jubelind.products.domain.ProductMother
import com.jubel.jubelind.products.domain.ProductRepository
import com.jubel.jubelind.shared.infrastructure.encodeToString
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.Matchers
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS

@TestInstance(PER_CLASS)
class ProductListAllShould {

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
        val existingProducts = ProductMother.instances(5)

        Given {
            repositoryWith(existingProducts)
            port(4567)
        } When {
            get("/product")
        } Then {
            statusCode(200)
            contentType(ContentType.JSON)
            body(Matchers.equalTo(existingProducts.encodeToString()))
        }
    }

    private fun repositoryWith(existingProducts: List<Product>) {
        val productRepository = injector.getInstance(ProductRepository::class.java)
        existingProducts.forEach {
            productRepository.createProduct(it)
        }
    }

}