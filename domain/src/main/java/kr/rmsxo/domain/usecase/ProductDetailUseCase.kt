package kr.rmsxo.domain.usecase

import kotlinx.coroutines.flow.Flow
import kr.rmsxo.domain.model.Product
import kr.rmsxo.domain.repository.ProductDetailRepository
import javax.inject.Inject

class ProductDetailUseCase @Inject constructor(
    private val repository: ProductDetailRepository
) {

    fun getProductDetail(productId: String) : Flow<Product> {
        return repository.getProductDetail(productId)
    }
}