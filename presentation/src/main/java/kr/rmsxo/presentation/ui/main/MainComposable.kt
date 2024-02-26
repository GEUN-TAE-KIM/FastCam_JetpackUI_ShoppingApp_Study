package kr.rmsxo.presentation.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kr.rmsxo.domain.model.Banner
import kr.rmsxo.domain.model.BannerList
import kr.rmsxo.domain.model.ModelType
import kr.rmsxo.domain.model.Product
import kr.rmsxo.presentation.R
import kr.rmsxo.presentation.ui.component.BannerCard
import kr.rmsxo.presentation.ui.component.BannerListCard
import kr.rmsxo.presentation.ui.component.ProductCard
import kr.rmsxo.presentation.viewmodel.MainViewMode

@Composable
fun MainInsideScreen(viewMode: MainViewMode) {

    val modelList by viewMode.modelList.collectAsState(initial = listOf())
    val columnCount by viewMode.columnCount.collectAsState()

    LazyVerticalGrid(columns = GridCells.Fixed(columnCount))
    {
        items(modelList.size, span = { index ->
            val item = modelList[index]
            val spanCount = getSpanCountByType(item.type, columnCount)
            GridItemSpan(spanCount)
        }) {
            when (val item = modelList[it]) {
                is Banner -> BannerCard(model = item)
                is BannerList -> BannerListCard(model = item)
                is Product -> ProductCard(product = item) {
                }
            }
        }
    }

}

private fun getSpanCountByType(type: ModelType, defaultColumnCount: Int): Int {
    return when(type) {
        ModelType.PRODUCT -> 1
        ModelType.BANNER -> defaultColumnCount
        ModelType.BANNER_LIST -> defaultColumnCount
    }
}


















