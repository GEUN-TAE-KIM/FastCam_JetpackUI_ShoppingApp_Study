package kr.rmsxo.presentation.ui.main

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kr.rmsxo.domain.model.Banner
import kr.rmsxo.domain.model.BannerList
import kr.rmsxo.domain.model.Carousel
import kr.rmsxo.domain.model.ModelType
import kr.rmsxo.domain.model.Product
import kr.rmsxo.domain.model.Ranking
import kr.rmsxo.presentation.ui.component.BannerCard
import kr.rmsxo.presentation.ui.component.BannerListCard
import kr.rmsxo.presentation.ui.component.CarouselCard
import kr.rmsxo.presentation.ui.component.ProductCard
import kr.rmsxo.presentation.ui.component.RankingCard
import kr.rmsxo.presentation.viewmodel.MainViewModel

@Composable
fun MainHomeScreen(viewModel: MainViewModel) {

    val modelList by viewModel.modelList.collectAsState(initial = listOf())
    val columnCount by viewModel.columnCount.collectAsState()

    LazyVerticalGrid(columns = GridCells.Fixed(columnCount))
    {
        items(modelList.size, span = { index ->
            val item = modelList[index]
            val spanCount = getSpanCountByType(item.type, columnCount)
            GridItemSpan(spanCount)
        }) {
            when (val item = modelList[it]) {
                is Banner -> BannerCard(model = item) { model ->
                    viewModel.openBanner(model)
                }

                is BannerList -> BannerListCard(model = item) { model ->
                    viewModel.openBannerList(model)
                }

                is Product -> ProductCard(product = item) { model ->
                    viewModel.openProduct(model)
                }

                is Carousel -> CarouselCard(model = item) { model ->
                    viewModel.openCarouselProduct(model)
                }

                is Ranking -> RankingCard(model = item) { model ->
                    viewModel.openRankingProduct(model)
                }
            }
        }
    }

}

private fun getSpanCountByType(type: ModelType, defaultColumnCount: Int): Int {
    return when (type) {
        ModelType.PRODUCT -> 1
        ModelType.BANNER -> defaultColumnCount
        ModelType.BANNER_LIST -> defaultColumnCount
        ModelType.CAROUSEL -> defaultColumnCount
        ModelType.RANKING -> defaultColumnCount
    }
}


















