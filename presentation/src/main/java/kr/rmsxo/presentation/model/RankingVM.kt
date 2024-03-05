package kr.rmsxo.presentation.model

import androidx.navigation.NavHostController
import kr.rmsxo.domain.model.Product
import kr.rmsxo.domain.model.Ranking
import kr.rmsxo.presentation.delegate.ProductDelegate

class RankingVM(model: Ranking, private val productDelegate: ProductDelegate) :
    PresentationVM<Ranking>(model) {

    fun openRankingProduct(navHostController: NavHostController, product: Product) {
        productDelegate.openProduct(navHostController, product)
        sendRankingLog()
    }

    private fun sendRankingLog() {

    }

}