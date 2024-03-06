package kr.rmsxo.presentation.viewmodel.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.rmsxo.domain.model.Product
import kr.rmsxo.domain.model.SearchKeyword
import kr.rmsxo.domain.usecase.SearchUseCase
import kr.rmsxo.presentation.delegate.ProductDelegate
import kr.rmsxo.presentation.model.ProductVM
import kr.rmsxo.presentation.ui.NavigationRouteName
import kr.rmsxo.presentation.ui.utils.NavigationUtils
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: SearchUseCase
) : ViewModel(), ProductDelegate {

    private val _searchResult = MutableStateFlow<List<ProductVM>>(listOf())
    val searchResult: StateFlow<List<ProductVM>> = _searchResult
    val searchKeywords = useCase.getSearchKeywords()

    fun search(keyword: String) {
        viewModelScope.launch {
            useCase.search(SearchKeyword(keyword = keyword)).collectLatest {
                _searchResult.emit(it.map(::convertToProductVM))
            }
        }
    }

    private fun convertToProductVM(product: Product): ProductVM {
        return ProductVM(product, this)
    }

    override fun openProduct(navHostController: NavHostController, product: Product) {
        NavigationUtils.navigation(navHostController, NavigationRouteName.PRODUCT_DETAIL, product)
    }

}