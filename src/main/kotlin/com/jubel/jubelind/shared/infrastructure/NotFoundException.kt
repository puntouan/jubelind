package com.jubel.jubelind.shared.infrastructure

class NotFoundException(override val message: String?): RuntimeException(message){

    val code = 404

}
