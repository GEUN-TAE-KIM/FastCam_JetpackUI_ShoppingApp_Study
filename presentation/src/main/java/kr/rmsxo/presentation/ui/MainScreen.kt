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
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kr.rmsxo.presentation.R
import kr.rmsxo.presentation.ui.theme.JetPack_ShoppingMallTheme
import kr.rmsxo.presentation.viewmodel.MainViewMode

sealed class MainNavigationItem(var route: String, val icon : ImageVector, var name: String) {
    object Main : MainNavigationItem("Main", Icons.Filled.Home ,"Main")
    object Category : MainNavigationItem("Category", Icons.Filled.Star ,"Category")
    object MyPage : MainNavigationItem("MyPage", Icons.Filled.AccountBox ,"MyPage")
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val viewModel = hiltViewModel<MainViewMode>()
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            Header(viewModel)
        },
        scaffoldState = scaffoldState,
        bottomBar = {
            MainBottomNavigationBar(navController = navController)
        }
    ) {
        MainNavigationScreen(navController = navController)
    }

}

@Composable
fun Header(viewModel: MainViewMode) {
    TopAppBar(
        title = { Text("My App")},
        actions = {
            IconButton(onClick = {
                viewModel.openSearchForm()
            }) {
                Icon(Icons.Filled.Search, "SearchIcon")
            }
        }
    )
}

@Composable
fun MainBottomNavigationBar(navController: NavHostController) {
    val bottomNavigationItems = listOf(
        MainNavigationItem.Main,
        MainNavigationItem.Category,
        MainNavigationItem.MyPage
    )
    BottomNavigation(
        backgroundColor = Color(0xFFEFB8C8),
        contentColor = Color(0xFFCCC2DC)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        bottomNavigationItems.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route ,
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
fun MainNavigationScreen(navController: NavHostController) {
    NavHost(navController = navController, startDestination = MainNavigationItem.Main.route) {
        composable(MainNavigationItem.Main.route) {
            Text(text = "1")
        }
        composable(MainNavigationItem.Category.route) {
            Text(text = "2")
        }
        composable(MainNavigationItem.MyPage.route) {
            Text(text = "3")
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