package kr.rmsxo.presentation.ui.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kr.rmsxo.presentation.model.ProductVM
import kr.rmsxo.presentation.ui.component.ProductCard
import kr.rmsxo.presentation.viewmodel.MainViewModel

@Composable
fun LikeScreen(
    navHostController: NavHostController,
    viewModel: MainViewModel
) {
    val likeProduct by viewModel.likeProducts.collectAsState(initial = listOf())

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(likeProduct.size) { index ->
            ProductCard(
                navHostController = navHostController,
                presentationVM = likeProduct[index] as ProductVM
            )
        }
    }
}