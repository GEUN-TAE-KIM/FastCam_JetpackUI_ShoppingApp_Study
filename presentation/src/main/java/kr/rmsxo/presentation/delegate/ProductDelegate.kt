package kr.rmsxo.presentation.delegate

import kr.rmsxo.domain.model.Product

interface ProductDelegate {

    fun openProduct(product: Product)
}