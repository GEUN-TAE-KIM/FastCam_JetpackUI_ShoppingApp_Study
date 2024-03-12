package kr.rmsxo.presentation.viewmodel

import android.app.Presentation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kr.rmsxo.domain.model.AccountInfo
import kr.rmsxo.domain.model.Banner
import kr.rmsxo.domain.model.BannerList
import kr.rmsxo.domain.model.BaseModel
import kr.rmsxo.domain.model.Carousel
import kr.rmsxo.domain.model.Category
import kr.rmsxo.domain.model.ModelType
import kr.rmsxo.domain.model.Product
import kr.rmsxo.domain.model.Ranking
import kr.rmsxo.domain.usecase.AccountUseCase
import kr.rmsxo.domain.usecase.CategoryUseCase
import kr.rmsxo.domain.usecase.LikeUseCase
import kr.rmsxo.domain.usecase.MainUseCase
import kr.rmsxo.presentation.delegate.BannerDelegate
import kr.rmsxo.presentation.delegate.CategoryDelegate
import kr.rmsxo.presentation.delegate.ProductDelegate
import kr.rmsxo.presentation.model.BannerListVM
import kr.rmsxo.presentation.model.BannerVM
import kr.rmsxo.presentation.model.CarouselVM
import kr.rmsxo.presentation.model.PresentationVM
import kr.rmsxo.presentation.model.ProductVM
import kr.rmsxo.presentation.model.RankingVM
import kr.rmsxo.presentation.ui.NavigationRouteName
import kr.rmsxo.presentation.ui.utils.NavigationUtils
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainUseCase: MainUseCase,
    categoryUseCase: CategoryUseCase,
    private val accountUseCase: AccountUseCase,
    likeUseCase: LikeUseCase
) : ViewModel(), ProductDelegate, BannerDelegate, CategoryDelegate {

    private val _columnCount = MutableStateFlow(DEFAULT_COLUMN_COUNT)
    val columnCount: StateFlow<Int> = _columnCount

    val modelList = mainUseCase.getModelList().map(::convertToPresentationVM)
    val categories = categoryUseCase.getCategories()

    val accountInfo = accountUseCase.getAccountInfo()

    val likeProducts = likeUseCase.getLikeProduct().map(::convertToPresentationVM)

    fun openSearchForm(navHostController: NavHostController) {
        NavigationUtils.navigation(navHostController, NavigationRouteName.SEARCH)
    }

    fun openBasket(navHostController: NavHostController) {
        NavigationUtils.navigation(navHostController, NavigationRouteName.BASKET)
    }

    fun signIn(accountInfo: AccountInfo) {
        viewModelScope.launch {
            accountUseCase.signIn(accountInfo)
        }
    }

    fun signOut() {
        viewModelScope.launch {
            accountUseCase.signOut()
        }
    }

    fun updateColumnCount(count: Int) {
        viewModelScope.launch {
            _columnCount.emit(count)
        }
    }

    override fun likeProduct(product: Product) {
        viewModelScope.launch {
            mainUseCase.likeProduct(product)
        }
    }

    override fun openProduct(navHostController: NavHostController, product: Product) {
        NavigationUtils.navigation(navHostController,NavigationRouteName.PRODUCT_DETAIL, product)

    }

    override fun openBanner(bannerId: String) {

    }

    override fun openCategory(navHostController: NavHostController, category: Category) {
        NavigationUtils.navigation(navHostController, NavigationRouteName.CATEGORY, category)
    }

    private fun convertToPresentationVM(list: List<BaseModel>): List<PresentationVM<out BaseModel>> {
        return list.map { model ->
            when (model) {
                is Product -> ProductVM(model, this)
                is Ranking -> RankingVM(model, this)
                is Carousel -> CarouselVM(model, this)
                is Banner -> BannerVM(model, this)
                is BannerList -> BannerListVM(model, this)
            }
        }
    }


    companion object {
        private const val DEFAULT_COLUMN_COUNT = 2
    }

}