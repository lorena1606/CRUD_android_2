package com.example.crud_android.data.remote.dto.req.product

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Review(
    @param:Json(name = "comment")
    val comment: String? = null,
    @param:Json(name = "date")
    val date: String? = null,
    @param:Json(name = "rating")
    val rating: Int? = null,
    @param:Json(name = "reviewerEmail")
    val reviewerEmail: String? = null,
    @param:Json(name = "reviewerName")
    val reviewerName: String? = null
)