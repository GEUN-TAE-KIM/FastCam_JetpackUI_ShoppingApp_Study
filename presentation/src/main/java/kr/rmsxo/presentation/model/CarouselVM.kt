package kr.rmsxo.presentation.model

import kr.rmsxo.domain.model.Carousel
import kr.rmsxo.domain.model.Product
import kr.rmsxo.presentation.delegate.ProductDelegate

class CarouselVM(model: Carousel, private val productDelegate: ProductDelegate) :
    PresentationVM<Carousel>(model) {

    fun openCarouselProduct(product: Product) {
        productDelegate.openProduct(product)
        sendCarouselLog()
    }

    fun sendCarouselLog() {

    }
}