package com.jubel.jubelind

import com.google.inject.Guice
import com.google.inject.Injector
import com.jubel.jubelind.shared.infrastructure.*
import kotlinx.serialization.SerializationException
import spark.Filter
import spark.Request
import spark.Response
import spark.Spark
import spark.Spark.awaitInitialization
import spark.Spark.exception
import spark.kotlin.after
import spark.kotlin.stop

class JubelIND {

    fun start(): Injector{
        val injector = Guice.createInjector()
        Controllers().create(injector)

        setDefaultContentType()
        exceptionsHandlers()

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
        val messageObj = Message(message ?: "NO MESSAGE")
        response.body(messageObj.encodeToString())
    }

}