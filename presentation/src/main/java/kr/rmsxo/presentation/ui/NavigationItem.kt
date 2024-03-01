package kr.rmsxo.presentation.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import kr.rmsxo.domain.model.Category
import kr.rmsxo.presentation.ui.NavigationRouteName.CATEGORY
import kr.rmsxo.presentation.ui.NavigationRouteName.MAIN_CATEGORY
import kr.rmsxo.presentation.ui.NavigationRouteName.MAIN_HOME
import kr.rmsxo.presentation.ui.NavigationRouteName.MAIN_MY_PAGE

sealed class NavigationItem(open val route: String) {
    sealed class MainNav(
        override val route: String,
        val icon: ImageVector,
        var name: String
    ) : NavigationItem(route) {

        object Home : MainNav(MAIN_HOME, Icons.Filled.Home, MAIN_HOME)
        object Category : MainNav(MAIN_CATEGORY, Icons.Filled.Star, MAIN_CATEGORY)
        object MyPage : MainNav(MAIN_MY_PAGE, Icons.Filled.AccountBox, MAIN_MY_PAGE)

        companion object {
            fun isMainRoute(route: String?): Boolean {
                return when (route) {
                    MAIN_HOME, MAIN_CATEGORY, MAIN_CATEGORY -> true
                    else -> false
                }
            }
        }

    }
    data class CategoryNav(val category: Category) : NavigationItem(CATEGORY)
}

object NavigationRouteName {
    const val DEEP_LINK_SCHEME = "fastcampus://"
    const val MAIN_HOME = "main_home"
    const val MAIN_CATEGORY = "main_category"
    const val MAIN_MY_PAGE = "main_my_page"
    const val MAIN_LIKE = "main_like"
    const val CATEGORY = "category"
    const val PRODUCT_DETAIL = "product_detail"
    const val SEARCH = "search"
    const val BASKET = "basket"
    const val PURCHASE_HISTORY = "purchase_history"
}