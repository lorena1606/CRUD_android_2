package com.example.crud_android.data.mapper

import com.example.crud_android.data.remote.dto.req.product.Product
import com.example.crud_android.domain.model.ProductModel


fun Product.toDomain(): ProductModel {
    return ProductModel (
        id = id,
        title = title ?: "Sin título",
        description = description ?: "Sin descripción",
        category = category ?: "General",
        price = price
    )
}