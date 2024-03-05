package kr.rmsxo.presentation.viewmodel.category

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kr.rmsxo.domain.model.Category
import kr.rmsxo.domain.model.Product
import kr.rmsxo.domain.usecase.CategoryUseCase
import kr.rmsxo.presentation.delegate.ProductDelegate
import kr.rmsxo.presentation.model.ProductVM
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val useCase: CategoryUseCase
) : ViewModel(), ProductDelegate {

    private val _products = MutableStateFlow<List<ProductVM>>(listOf())
    val products = MutableStateFlow<List<ProductVM>>(listOf())

    suspend fun updateCategory(category: Category) {
        useCase.getProductsByCategory(category).collectLatest {
            products.emit(convertToPresentationVM(it))
        }
    }

    override fun openProduct(product: Product) {

    }

    private fun convertToPresentationVM(list: List<Product>): List<ProductVM> {
        return list.map {
            ProductVM(it, this)
        }
    }
}