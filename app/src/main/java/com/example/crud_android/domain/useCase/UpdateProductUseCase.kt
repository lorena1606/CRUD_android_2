package com.example.crud_android.domain.useCase

import com.example.crud_android.data.remote.dto.req.product.Product
import com.example.crud_android.domain.model.ProductModel
import com.example.crud_android.domain.repository.ProductRepository
import javax.inject.Inject

class UpdateProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(
        id: Int,
        title: String,
        price: Double
    ): ProductModel {
        return repository.updateProduct(id, title, price)
    }
}