package kr.rmsxo.domain.usecase

import kotlinx.coroutines.flow.Flow
import kr.rmsxo.domain.model.BasketProduct
import kr.rmsxo.domain.model.Product
import kr.rmsxo.domain.repository.BasketRepository
import javax.inject.Inject

class BasketUseCase @Inject constructor(
    private val basketRepository: BasketRepository
) {

    fun getBasketProducts() :Flow<List<BasketProduct>> {
        return basketRepository.getBasketProducts()
    }

    suspend fun removeBasketProducts(product: Product) {
        basketRepository.removeBasketProduct(product = product)
    }

    suspend fun checkoutBasket(products: List<BasketProduct>) {
        basketRepository.checkoutBasket(products)
    }
}