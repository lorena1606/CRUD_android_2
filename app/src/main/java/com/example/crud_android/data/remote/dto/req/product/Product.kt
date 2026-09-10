package com.example.crud_android.data.remote.dto.req.product


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Product(
    @param:Json(name = "availabilityStatus")
    val availabilityStatus: String,
    @param:Json(name = "brand")
    val brand: String,
    @param:Json(name = "category")
    val category: String,
    @param:Json(name = "description")
    val description: String,
    @param:Json(name = "dimensions")
    val dimensions: Dimensions,
    @param:Json(name = "discountPercentage")
    val discountPercentage: Double,
    @param:Json(name = "id")
    val id: Int,
    @param:Json(name = "images")
    val images: List<String>,
    @param:Json(name = "meta")
    val meta: Meta,
    @param:Json(name = "minimumOrderQuantity")
    val minimumOrderQuantity: Int,
    @param:Json(name = "price")
    val price: Double,
    @param:Json(name = "rating")
    val rating: Double,
    @param:Json(name = "returnPolicy")
    val returnPolicy: String,
    @param:Json(name = "reviews")
    val reviews: List<Review>,
    @param:Json(name = "shippingInformation")
    val shippingInformation: String,
    @param:Json(name = "sku")
    val sku: String,
    @param:Json(name = "stock")
    val stock: Int,
    @param:Json(name = "tags")
    val tags: List<String>,
    @param:Json(name = "thumbnail")
    val thumbnail: String,
    @param:Json(name = "title")
    val title: String,
    @param:Json(name = "warrantyInformation")
    val warrantyInformation: String,
    @param:Json(name = "weight")
    val weight: Int
)