package com.jubel.jubelind.products.domain

import kotlin.math.roundToInt
import kotlin.random.Random

class ProductDataToUpdateMother {

    companion object{

        private val rnd = Random(System.currentTimeMillis())

        fun instance(
            name: String = "Name - ${System.currentTimeMillis()}",
            calories: Float = (rnd.nextFloat() * 100).roundToInt() / 100f,
            protein: Float = (rnd.nextFloat() * 100).roundToInt() / 100f,
            fat: Float = (rnd.nextFloat() * 100).roundToInt() / 100f,
            carbohydrates: Float = (rnd.nextFloat() * 100).roundToInt() / 100f,
        ): ProductDataToUpdate{
            return ProductDataToUpdate(name, calories, protein, fat, carbohydrates)
        }
    }

}