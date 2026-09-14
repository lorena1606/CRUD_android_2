package com.example.crud_android.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.crud_android.ui.section.ProductDetails
import com.example.crud_android.ui.viewModel.ProductViewModel

@Composable
fun ProductEditScreen(
    productId: Int,
    onNavigateBack: () -> Unit = {},
    viewModel: ProductViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(productId) {
        viewModel.getProductById(productId)
    }

    LaunchedEffect(uiState.isUpdateSuccess) {
        if (uiState.isUpdateSuccess) {
            onNavigateBack()
        }
    }

    ProductDetails(
        uiState = uiState,
        onUpdate = { title, price ->
            viewModel.updateProduct(productId, title, price)
        },
        onCancel = onNavigateBack
    )
}