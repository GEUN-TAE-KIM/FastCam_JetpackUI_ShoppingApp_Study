package kr.rmsxo.presentation.ui.category

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kr.rmsxo.domain.model.Category
import kr.rmsxo.domain.model.Product
import kr.rmsxo.presentation.ui.component.ProductCard
import kr.rmsxo.presentation.viewmodel.category.CategoryViewModel

@Composable
fun CategoryScreen(
    category: Category,
    viewModel: CategoryViewModel = hiltViewModel()
) {
    val products by viewModel.products.collectAsState()
    LaunchedEffect(key1 = category) {
        viewModel.updateCategory(category)
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(products.size) { index ->
            ProductCard(product = products[index]) {
                viewModel.openProduct(it)
            }

        }
    }
}