package com.example.crud_android.ui.state

import com.example.crud_android.data.remote.dto.req.product.Product
import com.example.crud_android.domain.model.ProductModel

data class ProductUIState(
    val isLoading: Boolean = false,
    val product: ProductModel? = null,
    val errorMessage: String? = null,
    val isUpdating: Boolean = false,
    val updateSuccessMessage: String? = null
)

