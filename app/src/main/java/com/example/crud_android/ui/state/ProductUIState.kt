package com.example.crud_android.ui.state

import com.example.crud_android.domain.model.ProductModel

data class ProductUIState(
    val isLoading: Boolean = false,
    val products: List<ProductModel> = emptyList(),
    val product: ProductModel? = null,
    val errorMessage: String? = null,
    val isUpdating: Boolean = false,
    val updateSuccessMessage: String? = null,
    val isUpdateSuccess: Boolean = false,
    val isCreateSuccess: Boolean = false
)