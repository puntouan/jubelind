package com.jubel.jubelind.products.domain

import com.jubel.jubelind.products.infrastructure.dtos.ProductToCreateDto
import com.jubel.jubelind.products.infrastructure.dtos.ProductToCreateDtoMapper
import kotlin.math.roundToInt
import kotlin.random.Random

class ProductToCreateMother {

    companion object{

        private val rnd = Random(System.currentTimeMillis())

        private val productToCreateDtoMapper = ProductToCreateDtoMapper()

        fun instanceDto(
            name: String = "Name - ${System.currentTimeMillis()}",
            calories: Float = (rnd.nextFloat() * 100).roundToInt() / 100f,
            protein: Float = (rnd.nextFloat() * 100).roundToInt() / 100f,
            fat: Float = (rnd.nextFloat() * 100).roundToInt() / 100f,
            carbohydrates: Float = (rnd.nextFloat() * 100).roundToInt() / 100f,
        ): ProductToCreateDto{
            return ProductToCreateDto(name, calories, protein, fat, carbohydrates)
        }

        fun instance(
            name: String = "Name - ${System.currentTimeMillis()}",
            calories: Float = (rnd.nextFloat() * 100).roundToInt() / 100f,
            protein: Float = (rnd.nextFloat() * 100).roundToInt() / 100f,
            fat: Float = (rnd.nextFloat() * 100).roundToInt() / 100f,
            carbohydrates: Float = (rnd.nextFloat() * 100).roundToInt() / 100f,
        ): ProductToCreate{
            val dto = instanceDto(name, calories, protein, fat, carbohydrates)
            return productToCreateDtoMapper.mapFromDtoToDomain(dto)
        }
    }

}