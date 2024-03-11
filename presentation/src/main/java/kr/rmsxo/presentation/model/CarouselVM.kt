package kr.rmsxo.presentation.model

import androidx.navigation.NavHostController
import kr.rmsxo.domain.model.Carousel
import kr.rmsxo.domain.model.Product
import kr.rmsxo.presentation.delegate.ProductDelegate

class CarouselVM(model: Carousel, private val productDelegate: ProductDelegate) :
    PresentationVM<Carousel>(model) {

    fun openCarouselProduct(navHostController: NavHostController, product: Product) {
        productDelegate.openProduct(navHostController,product)
        sendCarouselLog()
    }

    fun likeProduct(product: Product) {
        productDelegate.likeProduct(product)
    }

    fun sendCarouselLog() {

    }
}