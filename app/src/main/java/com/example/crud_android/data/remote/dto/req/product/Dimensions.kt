package com.example.crud_android.data.remote.dto.req.product

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Dimensions(
    @param:Json(name = "depth")
    val depth: Double? = null,
    @param:Json(name = "height")
    val height: Double? = null,
    @param:Json(name = "width")
    val width: Double? = null
)