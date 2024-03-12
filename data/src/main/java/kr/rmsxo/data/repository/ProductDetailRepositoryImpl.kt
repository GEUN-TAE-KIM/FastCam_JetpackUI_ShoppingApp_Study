package kr.rmsxo.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kr.rmsxo.data.datasource.ProductDataSource
import kr.rmsxo.data.db.dao.BasketDao
import kr.rmsxo.data.db.entity.toBasketProductEntity
import kr.rmsxo.domain.model.Product
import kr.rmsxo.domain.repository.ProductDetailRepository
import javax.inject.Inject

class ProductDetailRepositoryImpl @Inject constructor(
    private val dataSource: ProductDataSource,
    private val basketDao: BasketDao
) : ProductDetailRepository {

    override fun getProductDetail(productId: String): Flow<Product> {
        return dataSource.getHomeComponents().map { products ->
            products.filterIsInstance<Product>().first { it.productId == productId }
        }
    }

    override suspend fun addBasket(product: Product) {
        val alreadyExistProduct = basketDao.get(product.productId)
        if (alreadyExistProduct == null) {
            basketDao.insert(product.toBasketProductEntity())
        } else {
            basketDao.insert(alreadyExistProduct.copy(count = alreadyExistProduct.count + 1))
        }
    }
}