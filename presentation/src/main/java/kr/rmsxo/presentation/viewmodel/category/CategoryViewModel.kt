package kr.rmsxo.presentation.viewmodel.category

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kr.rmsxo.domain.model.Category
import kr.rmsxo.domain.model.Product
import kr.rmsxo.domain.usecase.CategoryUseCase
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val useCase: CategoryUseCase
): ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(listOf())
    val products = MutableStateFlow<List<Product>>(listOf())


    suspend fun updateCategory(category: Category) {
        useCase.getProductsByCategory(category).collectLatest {
            products.emit(it)
        }
    }

    fun openProduct(product: Product) {

    }
}