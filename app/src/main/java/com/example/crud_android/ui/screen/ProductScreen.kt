package com.example.crud_android.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.crud_android.ui.component.productCard
import com.example.crud_android.ui.viewModel.ProductViewModel

@Composable
fun ProductScreen(
    onEditNavigate: (Int) -> Unit = {},
    viewModel: ProductViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    
    var showDeleteDialog by remember { mutableStateOf(false) }
    var productToDeleteId by remember { mutableStateOf<Int?>(null) }

    var showCreateDialog by remember { mutableStateOf(false) }
    var newProductTitle by remember { mutableStateOf("") }
    var newProductPrice by remember { mutableStateOf("") }
    var newProductDescription by remember { mutableStateOf("") }
    var newProductCategory by remember { mutableStateOf("") }

    var searchIdText by remember { mutableStateOf("") }

    LaunchedEffect(uiState.isCreateSuccess) {
        if (uiState.isCreateSuccess) {
            showCreateDialog = false
            newProductTitle = ""
            newProductPrice = ""
            newProductDescription = ""
            newProductCategory = ""
        }
    }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { 
                showDeleteDialog = false 
                productToDeleteId = null
            },
            title = { Text(text = "⚠️ Confirmar Eliminación") },
            text = { Text(text = "¿Está seguro de que desea eliminar este producto? Esta acción no se puede deshacer. ❌") },
            confirmButton = {
                Button(
                    onClick = {
                        productToDeleteId?.let { id ->
                            viewModel.deleteProduct(id)
                        }
                        showDeleteDialog = false
                        productToDeleteId = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text(text = "Sí, Eliminar 🗑️", color = Color.White)
                }
            },
            dismissButton = {
                Button(onClick = { 
                    showDeleteDialog = false 
                    productToDeleteId = null
                }) {
                    Text(text = "Cancelar")
                }
            }
        )
    }

    if (showCreateDialog) {
        AlertDialog(
            onDismissRequest = { showCreateDialog = false },
            title = { Text(text = "➕ Añadir Nuevo Producto") },
            text = {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    item {
                        OutlinedTextField(
                            value = newProductTitle,
                            onValueChange = { newProductTitle = it },
                            label = { Text("Título del Producto ✏️") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    item {
                        OutlinedTextField(
                            value = newProductPrice,
                            onValueChange = { newProductPrice = it },
                            label = { Text("Precio 💰") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    item {
                        OutlinedTextField(
                            value = newProductDescription,
                            onValueChange = { newProductDescription = it },
                            label = { Text("Descripción 📝") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    item {
                        OutlinedTextField(
                            value = newProductCategory,
                            onValueChange = { newProductCategory = it },
                            label = { Text("Categoría 🏷️") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val priceDouble = newProductPrice.toDoubleOrNull() ?: 0.0
                        viewModel.createProduct(
                            title = newProductTitle,
                            price = priceDouble,
                            description = newProductDescription,
                            category = newProductCategory
                        )
                    }
                ) {
                    Text(text = "Guardar ✅")
                }
            },
            dismissButton = {
                Button(onClick = { showCreateDialog = false }) {
                    Text(text = "Cancelar")
                }
            }
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "📱 CRUD Productos",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            
            Button(onClick = { showCreateDialog = true }) {
                Text(text = "➕ Añadir")
            }
        }

        // Barra de búsqueda rápida por ID
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = searchIdText,
                onValueChange = { 
                    searchIdText = it 
                    if (it.trim().isEmpty()) {
                        viewModel.getAllProducts()
                    }
                },
                label = { Text("Buscar por ID 🔍") },
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = {
                    val idInt = searchIdText.trim().toIntOrNull()
                    if (idInt != null) {
                        viewModel.getProductById(idInt)
                    } else {
                        viewModel.getAllProducts()
                    }
                }
            ) {
                Text(text = "Buscar")
            }
        }

        if (uiState.isLoading && uiState.products.isEmpty()) {
            CircularProgressIndicator(modifier = Modifier.padding(32.dp))
            Text(text = "Procesando... 🔄")
        } else if (uiState.errorMessage != null && uiState.products.isEmpty()) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(text = uiState.errorMessage ?: "Error")
                Button(onClick = { 
                    searchIdText = ""
                    viewModel.getAllProducts() 
                }) {
                    Text(text = "Mostrar Todos 🔄")
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
                            onEditNavigate(product.id)
                        },
                        onDeleteClick = {
                            productToDeleteId = product.id
                            showDeleteDialog = true
                        }
                    )
                }
            }
        }
    }
}