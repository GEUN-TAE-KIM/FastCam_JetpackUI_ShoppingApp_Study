package kr.rmsxo.presentation.model

import kr.rmsxo.domain.model.Product
import kr.rmsxo.domain.model.Ranking
import kr.rmsxo.presentation.delegate.ProductDelegate

class RankingVM(model: Ranking, private val productDelegate: ProductDelegate) : PresentationVM<Ranking>(model) {

    fun openRankingProduct(product: Product) {
        productDelegate.openProduct(product)
        sendRankingLog()
    }

    private fun sendRankingLog() {

    }

}