package kr.rmsxo.presentation.ui.utils

import androidx.navigation.NavHostController
import kr.rmsxo.presentation.ui.BasketNav
import kr.rmsxo.presentation.ui.CategoryNav
import kr.rmsxo.presentation.ui.Destination
import kr.rmsxo.presentation.ui.MainNav
import kr.rmsxo.presentation.ui.NavigationRouteName
import kr.rmsxo.presentation.ui.ProductDetailNav
import kr.rmsxo.presentation.ui.PurchaseHistoryNav
import kr.rmsxo.presentation.ui.SearchNav

object NavigationUtils {

    fun navigate(
        controller: NavHostController,
        routeName: String,
        backStackRouteName: String? =null,
        isLaunchSingleTop: Boolean= true,
        needToRestoreState: Boolean= true
    ) {
        controller.navigate(routeName) {
            if(backStackRouteName != null) {
                popUpTo(backStackRouteName) { saveState = true}
            }
            launchSingleTop = isLaunchSingleTop
            restoreState = needToRestoreState
        }
    }

    fun findDestination(route : String?) : Destination {
        return when(route) {
            NavigationRouteName.MAIN_MY_PAGE -> MainNav.MyPage
            NavigationRouteName.MAIN_LIKE -> MainNav.Like
            NavigationRouteName.MAIN_HOME -> MainNav.Home
            NavigationRouteName.MAIN_CATEGORY -> MainNav.Category
            NavigationRouteName.SEARCH -> SearchNav
            NavigationRouteName.BASKET -> BasketNav
            NavigationRouteName.PURCHASE_HISTORY -> PurchaseHistoryNav

            ProductDetailNav.routeWithArgName() -> ProductDetailNav
            CategoryNav.routeWithArgName() -> CategoryNav
            else -> MainNav.Home
        }
    }
}