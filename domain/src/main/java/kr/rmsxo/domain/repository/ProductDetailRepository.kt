package kr.rmsxo.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.rmsxo.domain.model.Product

interface ProductDetailRepository {
    fun getProductDetail(productId: String) : Flow<Product>
}