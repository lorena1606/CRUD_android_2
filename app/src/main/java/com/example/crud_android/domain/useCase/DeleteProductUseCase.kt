package com.example.crud_android.domain.useCase

import com.example.crud_android.domain.model.ProductModel
import com.example.crud_android.domain.repository.ProductRepository
import javax.inject.Inject

class DeleteProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(id: Int): ProductModel {
        return repository.deleteProduct(id)
    }
}