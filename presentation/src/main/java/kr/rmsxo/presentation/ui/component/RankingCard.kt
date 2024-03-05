package kr.rmsxo.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kr.rmsxo.domain.model.Product
import kr.rmsxo.domain.model.Ranking
import kr.rmsxo.presentation.R
import kr.rmsxo.presentation.model.PresentationVM
import kr.rmsxo.presentation.model.RankingVM

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RankingCard(presentationVM: RankingVM) {
    val pagerState = rememberPagerState()
    val pageCount = presentationVM.model.productList.size / DEFAULT_RANKING_ITEM_COUNT

    Column {
        Text(
            text = presentationVM.model.title,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(10.dp,0.dp,0.dp,0.dp)
        )
        HorizontalPager(
            count = pageCount, state = pagerState,
            contentPadding = PaddingValues(end = 50.dp)
        ) { index ->
            Column {
                RankingProductCard(index * 3, presentationVM.model.productList[index * 3]) { product ->
                    presentationVM.openRankingProduct(product)
                }
                RankingProductCard(index * 3 + 1, presentationVM.model.productList[index * 3 + 1]) { product ->
                    presentationVM.openRankingProduct(product)
                }
                RankingProductCard(index * 3 + 2, presentationVM.model.productList[index * 3 + 2])  { product ->
                    presentationVM.openRankingProduct(product)
                }
            }
        }
    }
}

@Composable
fun RankingProductCard(index: Int, product: Product, onClick: (Product) -> Unit) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Text("${index + 1}", fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(0.dp,0.dp,10.dp,0.dp))
        Image(
            painter = painterResource(id = R.drawable.dream),
            "description",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(80.dp)
                .aspectRatio(0.7f)
        )
        Column(modifier = Modifier.padding(10.dp,0.dp,0.dp,0.dp)) {
            Text(
                fontSize = 14.sp,
                text = product.shop.shopName,
                modifier = Modifier.padding(0.dp,10.dp,0.dp,0.dp)
            )

            Text(
                fontSize = 14.sp,
                text = product.productName,
                modifier = Modifier.padding(0.dp,10.dp,0.dp,0.dp)
            )
            Price(product = product)
        }
    }
}

private const val DEFAULT_RANKING_ITEM_COUNT = 3