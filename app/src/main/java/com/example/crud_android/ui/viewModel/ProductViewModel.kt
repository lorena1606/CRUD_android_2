package com.example.crud_android.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crud_android.domain.useCase.DeleteProductUseCase
import com.example.crud_android.domain.useCase.GetAllProductsUseCase
import com.example.crud_android.domain.useCase.GetProductUseCase
import com.example.crud_android.domain.useCase.UpdateProductUseCase
import com.example.crud_android.ui.state.ProductUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getProductUseCase: GetProductUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
    private val deleteProductUseCase: DeleteProductUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(ProductUIState())
    val uiState: StateFlow<ProductUIState> = _uiState.asStateFlow()

    init {
        getAllProducts()
    }

    fun getAllProducts() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null,
                    isUpdateSuccess = false
                )
            }
            try {
                val list = getAllProductsUseCase()
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        products = list,
                        errorMessage = null
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "Error al cargar el listado"
                    )
                }
            }
        }
    }

    fun getProductById(id: Int){
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null,
                    updateSuccessMessage = null
                )
            }
            try {
                val result = getProductUseCase(id)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        product = result,
                        errorMessage = null
                    )
                }

            }catch (e: Exception){
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        product = null,
                        errorMessage = e.message ?: "Error al cargar el producto"
                    )
                }
            }
        }
    }

    fun updateProduct(id: Int, title: String, price: Double) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isUpdating = true,
                    updateSuccessMessage = null,
                    errorMessage = null
                )
            }
            try {
                val updatedProduct = updateProductUseCase(id, title, price)
                _uiState.update {
                    it.copy(
                        isUpdating = false,
                        product = updatedProduct,
                        updateSuccessMessage = "¡Producto actualizado exitosamente!",
                        isUpdateSuccess = true
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isUpdating = false,
                        errorMessage = e.message ?: "Error al actualizar el producto"
                    )
                }
            }
        }
    }

    fun deleteProduct(id: Int) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }
            try {
                deleteProductUseCase(id)
                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        products = state.products.filter { it.id != id },
                        errorMessage = null
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "Error al eliminar el producto"
                    )
                }
            }
        }
    }
}