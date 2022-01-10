package com.jubel.jubelind

import com.google.inject.Guice
import com.google.inject.Injector
import com.jubel.jubelind.shared.infrastructure.*
import com.jubel.jubelind.shared.infrastructure.dtos.MessageDto
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import spark.Response
import spark.Spark.awaitInitialization
import spark.Spark.exception
import spark.kotlin.after
import spark.kotlin.before
import spark.kotlin.options
import spark.kotlin.stop

class JubelIND {

    fun start(): Injector{
        val injector = Guice.createInjector()
        Controllers().create(injector)

        setDefaultContentType()
        exceptionsHandlers()
        allowCors()

        awaitInitialization()

        return injector
    }

    fun stopApp(){
        stop()
    }

    private fun setDefaultContentType() {
        after{
            response.type("application/json")
        }
    }

    private fun exceptionsHandlers(){
        exception(BadRequestException::class.java){
                exception, _, response ->
            response.status(400)
            setExceptionContentTypeAndMessage(response,exception.message)
        }
        exception(NotFoundException::class.java){
                exception, _, response ->
            response.status(404)
            setExceptionContentTypeAndMessage(response,exception.message)

        }
    }

    private fun setExceptionContentTypeAndMessage(response: Response, message: String?) {
        response.type("application/json")
        val message = MessageDto(message ?: "NO MESSAGE")
        response.body(Json.encodeToString(message))
    }

    private fun allowCors(){
        options("/*") {
            val accessControlRequestHeaders = request
                .headers("Access-Control-Request-Headers")
            if (accessControlRequestHeaders != null) {
                response.header(
                    "Access-Control-Allow-Headers",
                    accessControlRequestHeaders
                )
            }
            val accessControlRequestMethod = request
                .headers("Access-Control-Request-Method")
            if (accessControlRequestMethod != null) {
                response.header(
                    "Access-Control-Allow-Methods",
                    accessControlRequestMethod
                )
            }
            "OK"
        }

        before { response.header("Access-Control-Allow-Origin", "*") }
    }
}