package com.jubel.jubelind.shared.infrastructure

import com.google.inject.Injector
import com.jubel.jubelind.products.infrastructure.ProductCreationController
import com.jubel.jubelind.products.infrastructure.ProductDeletionController
import com.jubel.jubelind.products.infrastructure.ProductListAllController
import com.jubel.jubelind.products.infrastructure.ProductRetrievalController

class Controllers {

    fun create(injector: Injector){
        injector.getInstance(ProductCreationController::class.java)
        injector.getInstance(ProductRetrievalController::class.java)
        injector.getInstance(ProductDeletionController::class.java)
        injector.getInstance(ProductListAllController::class.java)
    }
}