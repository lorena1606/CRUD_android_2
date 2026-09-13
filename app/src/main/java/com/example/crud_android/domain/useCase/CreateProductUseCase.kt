package com.example.crud_android.domain.useCase

import com.example.crud_android.domain.model.ProductModel
import com.example.crud_android.domain.repository.ProductRepository
import javax.inject.Inject

class CreateProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(
        title: String,
        price: Double,
        description: String,
        category: String
    ): ProductModel {
        return repository.createProduct(title, price, description, category)
    }
}