package kr.rmsxo.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.rmsxo.domain.model.BasketProduct
import kr.rmsxo.domain.model.Product

interface BasketRepository {
    fun getBasketProducts() : Flow<List<BasketProduct>>

    suspend fun removeBasketProduct(product: Product)

    suspend fun checkoutBasket(products: List<BasketProduct>)
}