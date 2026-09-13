package com.example.crud_android.data.remote.dto.req.product

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductCreateReq(
    @param:Json(name = "title")
    val title: String,
    @param:Json(name = "price")
    val price: Double,
    @param:Json(name = "description")
    val description: String,
    @param:Json(name = "category")
    val category: String
)