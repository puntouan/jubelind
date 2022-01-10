package com.jubel.jubelind.products


import com.jubel.jubelind.JubelIND
import com.jubel.jubelind.products.domain.ProductToCreateMother
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
internal class ProductCreationShould {

    private val UUID_PATTERN = "[0-9a-fA-F]{8}(?:-[0-9a-fA-F]{4}){3}-[0-9a-fA-F]{12}"

    private lateinit var jubelIND: JubelIND

    @BeforeAll
    fun setUp(){
        jubelIND = JubelIND()
        jubelIND.start()
    }

    @AfterAll
    fun stop(){
        jubelIND.stopApp()
    }

    @Test
    fun `create a new product`() {
        val productForCreate = ProductToCreateMother.instanceDto()
        Given {
            port(4567)
            body(Json.encodeToString(productForCreate))
        } When {
            post("/product")
        } Then {
            statusCode(200)
            contentType(JSON)
            body("id", matchesPattern(UUID_PATTERN))
            body("name", equalTo(productForCreate.name))
            body("calories", equalTo(productForCreate.calories))
            body("protein", equalTo(productForCreate.protein))
            body("fat", equalTo(productForCreate.fat))
            body("carbohydrates", equalTo(productForCreate.carbohydrates))
        }
    }

    @Test
    fun `not allow create malformed products`(){
        Given {
            port(4567)
            body("""
                {
                    "name": "Berenjena",
                    "calories": "TREINTA Y TRES",
                    "protein": 3,
                    "fat": 2.5,
                    "carbohydrates": 4
                }                
            """.trimIndent())
        }When {
            post("/product")
        } Then {
            statusCode(400)
            contentType(JSON)
            body("message", containsString("Failed to parse type"))
        }
    }

}