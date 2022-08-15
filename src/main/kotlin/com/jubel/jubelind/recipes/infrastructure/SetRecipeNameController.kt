package com.jubel.jubelind.recipes.infrastructure

import com.google.inject.Inject
import com.jubel.jubelind.recipes.application.SetGramsOfProduct
import com.jubel.jubelind.recipes.application.SetRecipeName
import spark.kotlin.post
import spark.kotlin.put

class SetRecipeNameController @Inject constructor(
    private val setRecipeName: SetRecipeName
) {

    init {
        post("/recipe/:recipeId"){
            val recipeId: String = request.params(":recipeId")
            val name = request.queryParams("name")
            setRecipeName(recipeId, name)
        }
    }

    fun setRecipeName(recipeId: String, name: String){
        setRecipeName.run(recipeId, name)
    }

}