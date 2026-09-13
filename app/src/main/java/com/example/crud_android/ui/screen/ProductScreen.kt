package com.example.crud_android.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.crud_android.ui.component.productCard
import com.example.crud_android.ui.section.ProductDetails
import com.example.crud_android.ui.viewModel.ProductViewModel

@Composable
fun ProductScreen(
    onNavigateBack: () -> Unit = {},
    viewModel: ProductViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var isEditMode by remember { mutableStateOf(false) }
    var selectedProductId by remember { mutableStateOf<Int?>(null) }

    LaunchedEffect(uiState.isUpdateSuccess) {
        if (uiState.isUpdateSuccess) {
            isEditMode = false
            selectedProductId = null
            viewModel.getAllProducts() // Refrescar lista
            onNavigateBack()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        if (!isEditMode) {
            Text(
                text = "📱 Listado de Productos (\"Ver Todos\")",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )

            if (uiState.isLoading && uiState.products.isEmpty()) {
                CircularProgressIndicator(modifier = Modifier.padding(32.dp))
                Text(text = "Consumiendo API dummyjson... 🔄")
            } else if (uiState.errorMessage != null && uiState.products.isEmpty()) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(text = "❌ ${uiState.errorMessage}")
                    Button(onClick = { viewModel.getAllProducts() }) {
                        Text(text = "Reintentar 🔄")
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(uiState.products) { product ->
                        productCard(
                            product = product,
                            onEditClick = {
                                selectedProductId = product.id
                                viewModel.getProductById(product.id)
                                isEditMode = true
                            }
                        )
                    }
                }
            }
        } else {
            ProductDetails(
                uiState = uiState,
                onUpdate = { title, price ->
                    selectedProductId?.let { id ->
                        viewModel.updateProduct(id, title, price)
                    }
                },
                onCancel = { 
                    isEditMode = false 
                    selectedProductId = null
                }
            )
        }
    }
}