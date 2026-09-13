package com.example.crud_android.domain.repository

import com.example.crud_android.domain.model.ProductModel

interface ProductRepository {
    suspend fun getAllProducts(): List<ProductModel>

    suspend fun GetProductById(
        id: Int
    ): ProductModel

    suspend fun createProduct(
        title: String,
        price: Double,
        description: String,
        category: String
    ): ProductModel

    suspend fun updateProduct(
        id: Int,
        title: String,
        price: Double,
    ): ProductModel

    suspend fun deleteProduct(
        id: Int
    ): ProductModel
}