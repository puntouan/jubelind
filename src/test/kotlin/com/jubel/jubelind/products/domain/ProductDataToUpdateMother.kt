package com.jubel.jubelind.products.domain

import com.jubel.jubelind.products.infrastructure.dtos.ProductDataToUpdateDto
import com.jubel.jubelind.products.infrastructure.dtos.ProductDataToUpdateDtoMapper
import kotlin.math.roundToInt
import kotlin.random.Random

class ProductDataToUpdateMother {

    companion object{

        private val rnd = Random(System.currentTimeMillis())

        private val productDataToUpdateDtoMapper = ProductDataToUpdateDtoMapper()

        fun instanceDto(
            name: String = "Name - ${System.currentTimeMillis()}",
            calories: Float = (rnd.nextFloat() * 100).roundToInt() / 100f,
            protein: Float = (rnd.nextFloat() * 100).roundToInt() / 100f,
            fat: Float = (rnd.nextFloat() * 100).roundToInt() / 100f,
            carbohydrates: Float = (rnd.nextFloat() * 100).roundToInt() / 100f,
        ): ProductDataToUpdateDto{
            return ProductDataToUpdateDto(name, calories, protein, fat, carbohydrates)
        }

        fun instance(
            name: String = "Name - ${System.currentTimeMillis()}",
            calories: Float = (rnd.nextFloat() * 100).roundToInt() / 100f,
            protein: Float = (rnd.nextFloat() * 100).roundToInt() / 100f,
            fat: Float = (rnd.nextFloat() * 100).roundToInt() / 100f,
            carbohydrates: Float = (rnd.nextFloat() * 100).roundToInt() / 100f,
        ): ProductDataToUpdate{
            return productDataToUpdateDtoMapper.mapFromDtoToDomain(
                instanceDto(name,calories, protein, fat, carbohydrates)
            )
        }
    }

}