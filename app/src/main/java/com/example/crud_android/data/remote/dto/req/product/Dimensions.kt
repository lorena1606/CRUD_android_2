package com.example.crud_android.data.remote.dto.req.product


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Dimensions(
    @param:Json(name = "depth")
    val depth: Double,
    @param:Json(name = "height")
    val height: Double,
    @param:Json(name = "width")
    val width: Double
)