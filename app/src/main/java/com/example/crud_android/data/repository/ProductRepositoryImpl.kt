package com.example.crud_android.data.repository

import com.example.crud_android.data.mapper.toDomain
import com.example.crud_android.data.remote.api.ProductApiService
import com.example.crud_android.data.remote.dto.req.product.ProductCreateReq
import com.example.crud_android.data.remote.dto.req.product.ProductUpdateReq
import com.example.crud_android.domain.model.ProductModel
import com.example.crud_android.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: ProductApiService
): ProductRepository {
    override suspend fun getAllProducts(): List<ProductModel> {
        val response = api.getAllProducts()
        return response.products.map { it.toDomain() }
    }

    override suspend fun GetProductById(id: Int): ProductModel {
        val response = api.GetProductByid(id)
        return response.toDomain()
    }

    override suspend fun createProduct(
        title: String,
        price: Double,
        description: String,
        category: String
    ): ProductModel {
        val request = ProductCreateReq(
            title = title,
            price = price,
            description = description,
            category = category
        )
        val response = api.createProduct(request)
        return response.toDomain()
    }

    override suspend fun updateProduct(id: Int, title: String, price: Double): ProductModel {
        val request = ProductUpdateReq(title = title, price = price)
        val response = api.updateProduct(id, request)
        return response.toDomain()
    }

    override suspend fun deleteProduct(id: Int): ProductModel {
        val response = api.deleteProduct(id)
        return response.toDomain()
    }
}