package com.example.crud_android.ui.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.crud_android.ui.component.productCard
import com.example.crud_android.ui.state.ProductUIState

@Composable
fun ProductDetails(
    uiState: ProductUIState,
    onRetry: () -> Unit,
    onUpdate: (String, Double) -> Unit
) {
    when {
        uiState.isLoading -> {
            CircularProgressIndicator()
        }
        uiState.errorMessage != null -> {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = uiState.errorMessage ?: "Error en la carga del producto"
                )
                Button(
                    onClick = onRetry
                ) {
                    Text(text = "Reintentar")
                }
            }
        }
        uiState.product != null -> {
            val product = uiState.product

            var title by remember(product.title) { mutableStateOf(product.title) }
            var priceText by remember(product.price) { mutableStateOf(product.price.toString()) }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                productCard(
                    product = product
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Nuevo Título") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = priceText,
                    onValueChange = { priceText = it },
                    label = { Text("Nuevo Precio") },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        val priceDouble = priceText.toDoubleOrNull() ?: 0.0
                        onUpdate(title, priceDouble)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Actualizar Producto")
                }
            }
        }
    }
}