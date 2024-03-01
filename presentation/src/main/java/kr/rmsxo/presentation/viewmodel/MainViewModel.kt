package kr.rmsxo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kr.rmsxo.domain.model.Banner
import kr.rmsxo.domain.model.BannerList
import kr.rmsxo.domain.model.Category
import kr.rmsxo.domain.model.Product
import kr.rmsxo.domain.usecase.CategoryUseCase
import kr.rmsxo.domain.usecase.MainUseCase
import kr.rmsxo.presentation.ui.NavigationRouteName
import kr.rmsxo.presentation.ui.utils.NavigationUtils
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(mainUseCase: MainUseCase, categoryUseCase: CategoryUseCase) : ViewModel() {

    private val _columnCount = MutableStateFlow(DEFAULT_COLUMN_COUNT)
    val columnCount: StateFlow<Int> = _columnCount

    val modelList = mainUseCase.getModelList()
    val categories = categoryUseCase.getCategories()

    fun openSearchForm() {
        println("dsds")
    }

    fun updateColumnCount(count: Int) {
        viewModelScope.launch {
            _columnCount.emit(count)
        }
    }

    fun openProduct(product: Product) {

    }

    fun openCarouselProduct(carousel: Product) {

    }

    fun openRankingProduct(ranking: Product) {

    }

    fun openBanner(banner: Banner) {

    }

    fun openBannerList(bannerList: BannerList) {

    }

    fun openCategory(navHostController: NavHostController, category: Category) {
        NavigationUtils.navigation(navHostController, NavigationRouteName.CATEGORY, category)

    }



    companion object {
        private const val DEFAULT_COLUMN_COUNT = 2
    }

}