package com.example.crud_android.data.remote.api

import com.example.crud_android.data.remote.dto.req.product.Product
import com.example.crud_android.data.remote.dto.req.product.ProductCreateReq
import com.example.crud_android.data.remote.dto.req.product.ProductListResponse
import com.example.crud_android.data.remote.dto.req.product.ProductUpdateReq
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProductApiService {
    @GET("products")
    suspend fun getAllProducts(): ProductListResponse

    @GET("products/{id}")
    suspend fun GetProductByid(
        @Path("id") id: Int
    ) : Product

    @POST("products/add")
    suspend fun createProduct(
        @Body product: ProductCreateReq
    ): Product

    @PUT("products/{id}")
    suspend fun updateProduct(
        @Path("id") id: Int,
        @Body product: ProductUpdateReq
    ): Product

    @DELETE("products/{id}")
    suspend fun deleteProduct(
        @Path("id") id: Int
    ): Product
}