package com.jubel.jubelind.products.application

import com.jubel.jubelind.products.domain.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class ProductUpdateShould {

    @Mock
    private lateinit var productRepository: ProductRepository

    @Test
    fun `return updated product`() {
        //given
        val productIdToUpdate = "productId"
        val productDataToUpdate = ProductDataToUpdateMother.instance()

        `when`(productRepository.updateProduct(productIdToUpdate, productDataToUpdate)).thenReturn(
            Product(
                ProductId(productIdToUpdate),
                productDataToUpdate.name,
                productDataToUpdate.calories,
                productDataToUpdate.protein,
                productDataToUpdate.fat,
                productDataToUpdate.carbohydrates,
            )
        )

        // when
        val productUpdated = ProductUpdate(productRepository).update(
            productIdToUpdate,productDataToUpdate
        )

        // then
        assertThat(productUpdated.id.value).isEqualTo(productIdToUpdate)
        assertThat(productUpdated.name).isEqualTo(productDataToUpdate.name)
        assertThat(productUpdated.calories).isEqualTo(productDataToUpdate.calories)
        assertThat(productUpdated.protein).isEqualTo(productDataToUpdate.protein)
        assertThat(productUpdated.fat).isEqualTo(productDataToUpdate.fat)
        assertThat(productUpdated.carbohydrates).isEqualTo(productDataToUpdate.carbohydrates)

    }

    @Test
    fun `give error when productId to update does not exist`(){

        // given
        val nonExistingProductId = "non-existing-product-id"
        val productDataToUpdate = ProductDataToUpdateMother.instance()

        `when`(productRepository.updateProduct(nonExistingProductId, productDataToUpdate)).thenThrow(NonExistingProductException())

        assertThrows(NonExistingProductException::class.java){
            ProductUpdate(productRepository).update(nonExistingProductId, productDataToUpdate)
        }
        verify(productRepository).updateProduct(nonExistingProductId, productDataToUpdate)
    }
}