package com.jubel.jubelind.recipes.domain

import java.util.*
import kotlin.random.Random

class RecipeMother {

    companion object {

        private val rnd = Random(System.currentTimeMillis())

        fun instance(
            id: String = UUID.randomUUID().toString(),
            name: String = "Name - ${System.currentTimeMillis()}",
            productGrams: List<ProductGrams> = ProductGramsMother.instances(rnd.nextInt(5))
        ): Recipe {
            return Recipe(RecipeId(id), name, productGrams)
        }

        fun instances(n: Int): List<Recipe>{
            val recipes = mutableListOf<Recipe>()
            repeat(n){recipes.add(instance())}
            return recipes
        }

    }
}