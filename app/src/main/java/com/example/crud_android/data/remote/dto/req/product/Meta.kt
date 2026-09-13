package com.example.crud_android.data.remote.dto.req.product

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Meta(
    @param:Json(name = "barcode")
    val barcode: String? = null,
    @param:Json(name = "createdAt")
    val createdAt: String? = null,
    @param:Json(name = "qrCode")
    val qrCode: String? = null,
    @param:Json(name = "updatedAt")
    val updatedAt: String? = null
)