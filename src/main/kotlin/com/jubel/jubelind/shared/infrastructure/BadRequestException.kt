package com.jubel.jubelind.shared.infrastructure

class BadRequestException(override val message: String?, throwable: Throwable): RuntimeException(message, throwable){

    val code = 400

}
