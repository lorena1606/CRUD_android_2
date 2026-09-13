package com.example.crud_android.data.remote.dto.req.product

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Product(
    @param:Json(name = "availabilityStatus")
    val availabilityStatus: String? = null,
    @param:Json(name = "brand")
    val brand: String? = null,
    @param:Json(name = "category")
    val category: String? = null,
    @param:Json(name = "description")
    val description: String? = null,
    @param:Json(name = "dimensions")
    val dimensions: Dimensions? = null,
    @param:Json(name = "discountPercentage")
    val discountPercentage: Double? = null,
    @param:Json(name = "id")
    val id: Int,
    @param:Json(name = "images")
    val images: List<String>? = null,
    @param:Json(name = "meta")
    val meta: Meta? = null,
    @param:Json(name = "minimumOrderQuantity")
    val minimumOrderQuantity: Int? = null,
    @param:Json(name = "price")
    val price: Double? = null,
    @param:Json(name = "rating")
    val rating: Double? = null,
    @param:Json(name = "returnPolicy")
    val returnPolicy: String? = null,
    @param:Json(name = "reviews")
    val reviews: List<Review>? = null,
    @param:Json(name = "shippingInformation")
    val shippingInformation: String? = null,
    @param:Json(name = "sku")
    val sku: String? = null,
    @param:Json(name = "stock")
    val stock: Int? = null,
    @param:Json(name = "tags")
    val tags: List<String>? = null,
    @param:Json(name = "thumbnail")
    val thumbnail: String? = null,
    @param:Json(name = "title")
    val title: String? = null,
    @param:Json(name = "warrantyInformation")
    val warrantyInformation: String? = null,
    @param:Json(name = "weight")
    val weight: Int? = null
)