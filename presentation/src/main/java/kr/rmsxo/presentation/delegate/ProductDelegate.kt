package kr.rmsxo.presentation.delegate

import androidx.navigation.NavHostController
import kr.rmsxo.domain.model.Product

interface ProductDelegate {

    fun openProduct(navHostController: NavHostController, product: Product)
}