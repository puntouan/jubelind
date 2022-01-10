package com.jubel.jubelind.products.infrastructure.dtos

import com.jubel.jubelind.products.domain.Product
import com.jubel.jubelind.products.domain.ProductId
import kotlinx.serialization.Serializable
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.factory.Mappers

@Serializable
data class ProductDto(
    val id: String,
    val name: String,
    val calories: Float,
    val protein: Float,
    val fat: Float,
    val carbohydrates: Float
)

@Mapper
abstract class ProductDtoMapper{

    @Mapping(source = "id.value", target = "id")
    abstract fun mapFromDomainToDto(product: Product):ProductDto

    @Mapping(source = "id", target = "id", qualifiedByName = ["toProductId"])
    abstract fun mapFromDtoToDomain(productDto: ProductDto):Product

    @Named("toProductId")
    fun toProductId(id: String): ProductId{
        return ProductId(id)
    }

}

fun Product.mapFromDomainToDto(): ProductDto =
    Mappers.getMapper(ProductDtoMapper::class.java).mapFromDomainToDto(this)

fun ProductDto.mapFromDtoToDomain(): Product =
    Mappers.getMapper(ProductDtoMapper::class.java).mapFromDtoToDomain(this)

fun List<Product>.mapFromDomainToDto(): List<ProductDto> =
    this.map { it.mapFromDomainToDto()}

fun List<ProductDto>.mapFromDtoToDomain(): List<Product> =
    this.map { it.mapFromDtoToDomain()}