package com.jubel.jubelind.recipes.domain

import kotlin.random.Random

class RecipeToCreateMother {

    companion object{

        private val rnd = Random(System.currentTimeMillis())

        fun instance(
            name: String = "Name - ${System.currentTimeMillis()}"
        ): RecipeToCreate {
            return RecipeToCreate(name)
        }
    }

}