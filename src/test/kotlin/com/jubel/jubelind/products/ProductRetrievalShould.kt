package com.jubel.jubelind.products


import com.google.inject.Injector
import com.jubel.jubelind.JubelIND
import com.jubel.jubelind.products.domain.Product
import com.jubel.jubelind.products.domain.ProductMother
import com.jubel.jubelind.products.domain.ProductRepository
import com.jubel.jubelind.products.infrastructure.dtos.ProductDtoMapper
import com.jubel.jubelind.shared.SQLiteBase
import com.jubel.jubelind.shared.TestSQLiteModule
import io.restassured.http.ContentType.JSON
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.hamcrest.Matchers.containsString
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS

@TestInstance(PER_CLASS)
internal class ProductRetrievalShould: SQLiteBase() {

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
    fun `give an existing product`() {
        val existingProduct = ProductMother.instance()

        Given {
            repositoryWith(existingProduct)
            port(4567)
        } When {
            get("/product/${existingProduct.id.value}")
        } Then {
            statusCode(200)
            contentType(JSON)
            body(equalTo(Json.encodeToString(ProductDtoMapper().mapFromDomainToDto(existingProduct))))
        }
    }

    private fun repositoryWith(existingProduct: Product) {
        val productRepository = injector.getInstance(ProductRepository::class.java)
        println("Repo modificado para getting: " + productRepository)
        productRepository.createProduct(existingProduct)
    }

    @Test
    fun `return error when getting a non-existing product`() {
        Given {
            port(4567)
        } When {
            get("/product/non-existing-id")
        } Then {
            statusCode(404)
            contentType(JSON)
            body("message", containsString("No such product: non-existing-id"))
        }
    }

}