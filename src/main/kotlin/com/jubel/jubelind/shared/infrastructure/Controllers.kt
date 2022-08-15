package com.jubel.jubelind.shared.infrastructure

import com.google.inject.Injector
import com.jubel.jubelind.products.infrastructure.*
import com.jubel.jubelind.recipes.infrastructure.*

class Controllers {

    fun create(injector: Injector){
        injector.getInstance(ProductCreationController::class.java)
        injector.getInstance(ProductRetrievalController::class.java)
        injector.getInstance(ProductDeletionController::class.java)
        injector.getInstance(ProductListAllController::class.java)
        injector.getInstance(ProductFindByNameController::class.java)
        injector.getInstance(ProductUpdateController::class.java)
        injector.getInstance(RecipeCreationController::class.java)
        injector.getInstance(RecipeFindByByNamePaginatedController::class.java)
        injector.getInstance(SetGramsOfProductController::class.java)
        injector.getInstance(GetRecipeByIdController::class.java)
        injector.getInstance(DeleteQuantityProductController::class.java)
        injector.getInstance(SetRecipeNameController::class.java)
        injector.getInstance(AddQuantityProductController::class.java)
        injector.getInstance(GetRecipeMacroDataByIdController::class.java)
        injector.getInstance(GetProductMacroDataByIdController::class.java)

    }
}