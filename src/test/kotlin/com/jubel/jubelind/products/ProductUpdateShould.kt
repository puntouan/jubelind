package com.jubel.jubelind.products


import com.google.inject.Injector
import com.jubel.jubelind.JubelIND
import com.jubel.jubelind.products.domain.*
import io.restassured.http.ContentType.JSON
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS

@TestInstance(PER_CLASS)
internal class ProductUpdateShould {

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
    fun `update a product`() {
        val existingProduct = ProductMother.instance()
        val productDataToUpdate = ProductDataToUpdateMother.instance()

        Given {
            repositoryWith(existingProduct)
            port(4567)
            body(Json.encodeToString(productDataToUpdate))
        } When {
            post("/product/${existingProduct.id.value}")
        } Then {
            statusCode(200)
            contentType(JSON)
            body("id", equalTo(existingProduct.id.value))
            body("name", equalTo(productDataToUpdate.name))
            body("calories", equalTo(productDataToUpdate.calories))
            body("protein", equalTo(productDataToUpdate.protein))
            body("fat", equalTo(productDataToUpdate.fat))
            body("carbohydrates", equalTo(productDataToUpdate.carbohydrates))
        }
    }

    @Test
    fun `return error when updating a non-existing product`(){
        val productDataToUpdate = ProductDataToUpdateMother.instance()

        Given {
            port(4567)
            body(Json.encodeToString(productDataToUpdate))
        } When {
            post("/product/non-existing-id")
        } Then {
            statusCode(404)
            contentType(JSON)
            body("message", containsString("No such product: non-existing-id"))
        }
    }

    private fun repositoryWith(existingProduct: Product) {
        val productRepository = injector.getInstance(ProductRepository::class.java)
        productRepository.createProduct(existingProduct)
    }

}