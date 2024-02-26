package kr.rmsxo.presentation.ui.main

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kr.rmsxo.presentation.ui.common.ProductCard
import kr.rmsxo.presentation.viewmodel.MainViewMode

@Composable
fun MainInsideScreen(viewMode: MainViewMode) {

    val productList by viewMode.productList.collectAsState(initial = listOf())
    val columnCount by viewMode.columnCount.collectAsState()

    LazyVerticalGrid(columns = GridCells.Fixed(columnCount))
    {
        items(productList.size) {
            ProductCard(
                product = productList[it]
            ) {
                // 상세 화면 개발 시 추가
            }
        }
    }

}