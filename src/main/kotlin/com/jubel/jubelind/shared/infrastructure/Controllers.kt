package com.jubel.jubelind.shared.infrastructure

import com.google.inject.Injector
import com.jubel.jubelind.products.infrastructure.*

class Controllers {

    fun create(injector: Injector){
        injector.getInstance(ProductCreationController::class.java)
        injector.getInstance(ProductRetrievalController::class.java)
        injector.getInstance(ProductDeletionController::class.java)
        injector.getInstance(ProductListAllController::class.java)
        injector.getInstance(ProductFindByNameController::class.java)
        injector.getInstance(ProductUpdateController::class.java)
    }
}