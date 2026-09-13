package com.example.crud_android.ui.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.crud_android.ui.state.ProductUIState

@Composable
fun ProductDetails(
    uiState: ProductUIState,
    onUpdate: (String, Double) -> Unit,
    onCancel: () -> Unit = {}
) {
    val product = uiState.product

    if (product != null) {
        var title by remember(product.id) { mutableStateOf(product.title) }
        var priceText by remember(product.id) { mutableStateOf(product.price.toString()) }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "📝 Actualizar Producto",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Nuevo Título ✏️") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = priceText,
                onValueChange = { priceText = it },
                label = { Text("Nuevo Precio 💰") },
                modifier = Modifier.fillMaxWidth()
            )

            if (uiState.isUpdating) {
                CircularProgressIndicator()
                Text(text = "Actualizando en la API... 🔄")
            } else {
                Button(
                    onClick = {
                        val priceDouble = priceText.toDoubleOrNull() ?: 0.0
                        onUpdate(title, priceDouble)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Actualizar Producto 🔄")
                }
                
                Button(
                    onClick = onCancel,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "❌ Cancelar")
                }
            }

            uiState.updateSuccessMessage?.let {
                Text(text = "✅ $it", color = Color(0xFF4CAF50))
            }

            if (uiState.errorMessage != null) {
                Text(text = "❌ ${uiState.errorMessage}", color = Color.Red)
            }
        }
    }
}