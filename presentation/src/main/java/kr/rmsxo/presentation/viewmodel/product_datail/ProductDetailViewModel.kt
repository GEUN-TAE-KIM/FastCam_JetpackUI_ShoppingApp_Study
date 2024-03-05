package kr.rmsxo.presentation.viewmodel.product_datail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kr.rmsxo.domain.model.Product
import kr.rmsxo.domain.usecase.ProductDetailUseCase
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val useCase: ProductDetailUseCase
) : ViewModel() {

    private val _product = MutableStateFlow<Product?>(null)
    val product : StateFlow<Product?> = _product

    suspend fun updateProduct(productId: String) {
        useCase.getProductDetail(productId).collectLatest {
            _product.emit(it)
        }
    }

    fun addBasket(productId: String) {

    }
}