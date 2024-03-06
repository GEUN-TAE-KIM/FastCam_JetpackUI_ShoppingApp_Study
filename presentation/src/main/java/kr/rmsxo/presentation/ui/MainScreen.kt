package kr.rmsxo.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import kr.rmsxo.domain.model.Category
import kr.rmsxo.presentation.ui.category.CategoryScreen
import kr.rmsxo.presentation.ui.main.MainCategoryScreen
import kr.rmsxo.presentation.ui.main.MainHomeScreen
import kr.rmsxo.presentation.ui.product_detail.ProductDetailScreen
import kr.rmsxo.presentation.ui.search.SearchScreen
import kr.rmsxo.presentation.ui.theme.JetPack_ShoppingMallTheme
import kr.rmsxo.presentation.viewmodel.MainViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val viewModel = hiltViewModel<MainViewModel>()
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
           if(NavigationItem.MainNav.isMainRoute(currentRoute)) {
               MainHeader(viewModel, navController)
           }
        },
        scaffoldState = scaffoldState,
        bottomBar = {
            if (NavigationItem.MainNav.isMainRoute(currentRoute)) {
                MainBottomNavigationBar(navController, currentRoute)
            }
        }
    ) {
        MainNavigationScreen(viewMode = viewModel, navController = navController)
    }

}

@Composable
fun MainHeader(viewModel: MainViewModel, navController: NavHostController) {
    TopAppBar(
        title = { Text("My App") },
        actions = {
            IconButton(onClick = {
                viewModel.openSearchForm(navController)
            }) {
                Icon(Icons.Filled.Search, "SearchIcon")
            }
        }
    )
}

@Composable
fun MainBottomNavigationBar(navController: NavHostController, currentRoute: String?) {
    val bottomNavigationItems = listOf(
        NavigationItem.MainNav.Home,
        NavigationItem.MainNav.Category,
        NavigationItem.MainNav.MyPage
    )
    BottomNavigation(
        backgroundColor = Color(0xFFEFB8C8),
        contentColor = Color(0xFFCCC2DC)
    ) {

        bottomNavigationItems.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        item.icon,
                        item.route
                    )
                }
            )
        }

    }

}

@Composable
fun MainNavigationScreen(viewMode: MainViewModel, navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationRouteName.MAIN_HOME) {
        composable(NavigationRouteName.MAIN_HOME) {
            MainHomeScreen(navController, viewMode)
        }
        composable(NavigationRouteName.MAIN_CATEGORY) {
            MainCategoryScreen(viewMode, navController)
        }
        composable(NavigationRouteName.MAIN_MY_PAGE) {
            Text(text = "3")
        }
        composable(
            NavigationRouteName.CATEGORY + "{/category}",
            arguments = listOf(navArgument("category") {
                type = NavType.StringType
            })
        ) {
            val categoryString = it.arguments?.getString("category")
            val category =
                Gson().fromJson(categoryString, Category::class.java)
            if (category != null) {
                CategoryScreen(navHostController = navController, category = category)
            }
        }

        composable(
            NavigationRouteName.PRODUCT_DETAIL + "/{product}",
            arguments = (listOf(navArgument("product") { type = NavType.StringType }))
        ) {
            val productString = it.arguments?.getString("product")

            if (productString != null) {
                ProductDetailScreen(productString)
            }
        }

        composable(NavigationRouteName.SEARCH) {
            SearchScreen(navController)
        }

    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetPack_ShoppingMallTheme {
        MainScreen()
    }
}