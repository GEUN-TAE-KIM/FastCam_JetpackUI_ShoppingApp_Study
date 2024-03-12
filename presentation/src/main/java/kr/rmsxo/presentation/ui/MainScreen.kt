package kr.rmsxo.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
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
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.gson.Gson
import kr.rmsxo.domain.model.Category
import kr.rmsxo.presentation.ui.basket.BasketScreen
import kr.rmsxo.presentation.ui.category.CategoryScreen
import kr.rmsxo.presentation.ui.main.LikeScreen
import kr.rmsxo.presentation.ui.main.MainCategoryScreen
import kr.rmsxo.presentation.ui.main.MainHomeScreen
import kr.rmsxo.presentation.ui.main.MyPageScreen
import kr.rmsxo.presentation.ui.product_detail.ProductDetailScreen
import kr.rmsxo.presentation.ui.search.SearchScreen
import kr.rmsxo.presentation.ui.theme.JetPack_ShoppingMallTheme
import kr.rmsxo.presentation.ui.utils.NavigationUtils
import kr.rmsxo.presentation.viewmodel.MainViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(googleSignInClient: GoogleSignInClient) {
    val viewModel = hiltViewModel<MainViewModel>()
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (MainNav.isMainRoute(currentRoute)) {
                MainHeader(viewModel, navController, currentRoute)
            }
        },
        scaffoldState = scaffoldState,
        bottomBar = {
            if (MainNav.isMainRoute(currentRoute)) {
                MainBottomNavigationBar(navController, currentRoute)
            }
        }
    ) {
        MainNavigationScreen(viewModel = viewModel, navController = navController, googleSignInClient, scaffoldState)
    }

}

@Composable
fun MainHeader(viewModel: MainViewModel, navController: NavHostController, currentRoute: String?) {
    TopAppBar(
        title = { Text(NavigationUtils.findDestination(currentRoute).title) },
        navigationIcon = if (!MainNav.isMainRoute(currentRoute)) {
            {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "back")
                }
            }
        } else {
            null
        },
        actions = {
            if (MainNav.isMainRoute(currentRoute)) {
                IconButton(onClick = {
                    viewModel.openSearchForm(navController)
                }) {
                    Icon(Icons.Filled.Search, "SearchIcon")
                }
                IconButton(onClick = {
                    viewModel.openBasket(navController)
                }) {
                    Icon(Icons.Filled.ShoppingCart, "ShoppingIcon")
                }
            }
        }
    )
}

@Composable
fun MainBottomNavigationBar(navController: NavHostController, currentRoute: String?) {
    val bottomNavigationItems = listOf(
        MainNav.Home,
        MainNav.Category,
        MainNav.Like,
        MainNav.MyPage
    )
    BottomNavigation(
        backgroundColor = Color(0xFFEFB8C8),
        contentColor = Color(0xFFCCC2DC)
    ) {

        bottomNavigationItems.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        item.icon,
                        item.route
                    )
                },
                selected = currentRoute == item.route,
                onClick = {
                    NavigationUtils.navigate(
                        navController, item.route,
                        navController.graph.startDestinationRoute
                    )
                }
            )
        }
    }

}

@Composable
fun MainNavigationScreen(
    viewModel: MainViewModel,
    navController: NavHostController,
    googleSignInClient: GoogleSignInClient,
    scaffoldState: ScaffoldState
) {
    NavHost(navController = navController, startDestination = MainNav.Home.route) {
        composable(
            route = MainNav.Home.route,
            deepLinks = MainNav.Home.deepLinks
        ) {
            MainHomeScreen(navController, viewModel)
        }
        composable(
            route = MainNav.Category.route,
            deepLinks = MainNav.Category.deepLinks
        ) {
            MainCategoryScreen(viewModel, navController)
        }
        composable(
            route = MainNav.Like.route,
            deepLinks = MainNav.Like.deepLinks
        ) {
            LikeScreen(navController, viewModel)
        }
        composable(
            route = MainNav.MyPage.route,
            deepLinks = MainNav.MyPage.deepLinks
        ) {
            MyPageScreen(viewModel = viewModel, googleSignInClient = googleSignInClient, navHostController = navController)
        }
        composable(
            route = BasketNav.route,
            deepLinks = BasketNav.deepLinks
        ) {
            BasketScreen(scaffoldState)
        }

        composable(
            route = SearchNav.route,
            deepLinks = SearchNav.deepLinks
        ) {
            SearchScreen(navController)
        }
        composable(
            route = CategoryNav.routeWithArgName(),
            arguments = CategoryNav.arguments,
            deepLinks = CategoryNav.deepLinks
        ) {
            val category = CategoryNav.findArgument(it)
            if (category != null) {
                CategoryScreen(navHostController = navController, category = category)
            }
        }
        composable(
            route = ProductDetailNav.routeWithArgName(),
            arguments = ProductDetailNav.arguments,
            deepLinks = ProductDetailNav.deepLinks
        ) {
            val productString = ProductDetailNav.findArgument(it)
            if (productString != null) {
                ProductDetailScreen(productString)
            }
        }
    }
}
