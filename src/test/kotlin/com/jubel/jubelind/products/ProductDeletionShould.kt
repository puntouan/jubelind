package com.jubel.jubelind.products


import com.google.inject.Injector
import com.jubel.jubelind.JubelIND
import com.jubel.jubelind.products.domain.Product
import com.jubel.jubelind.products.domain.ProductMother
import com.jubel.jubelind.products.domain.ProductRepository
import io.restassured.http.ContentType.JSON
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS

@TestInstance(PER_CLASS)
internal class ProductDeletionShould {

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
    fun `delete an existing product`() {
        val existingProduct = ProductMother.instance()

        Given {
            repositoryWith(existingProduct)
            port(4567)
        } When {
            delete("/product/${existingProduct.id.value}")
        } Then {
            statusCode(204)
            contentType(JSON)
            body(`is`(emptyOrNullString()))
        }
    }

    private fun repositoryWith(existingProduct: Product) {
        val productRepository = injector.getInstance(ProductRepository::class.java)
        println("Repo modificado para borrado: " + productRepository)
        productRepository.createProduct(existingProduct)
    }

    @Test
    fun `return error when getting a non-existing product`() {
        Given {
            port(4567)
        } When {
            delete("/product/non-existing-id")
        } Then {
            statusCode(404)
            contentType(JSON)
            body("message", containsString("No such product: non-existing-id"))
        }
    }

}