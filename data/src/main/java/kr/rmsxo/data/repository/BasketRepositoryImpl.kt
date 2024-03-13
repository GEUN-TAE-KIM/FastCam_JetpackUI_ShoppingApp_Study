package kr.rmsxo.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kr.rmsxo.data.db.dao.BasketDao
import kr.rmsxo.data.db.dao.PurchaseHistoryDao
import kr.rmsxo.data.db.entity.PurchaseHistoryEntity
import kr.rmsxo.data.db.entity.toDomainModel
import kr.rmsxo.domain.model.BasketProduct
import kr.rmsxo.domain.model.Product
import kr.rmsxo.domain.repository.BasketRepository
import java.time.ZonedDateTime
import javax.inject.Inject

class BasketRepositoryImpl @Inject constructor(
    private val basketDao: BasketDao,
    private val purchaseHistoryDao: PurchaseHistoryDao,
) : BasketRepository{
    override fun getBasketProducts(): Flow<List<BasketProduct>> {
        return basketDao.getAll().map { list ->
            list.map { BasketProduct(it.toDomainModel(),it.count) }
        }
    }

    override suspend fun removeBasketProduct(product: Product) {
        return basketDao.delete(product.productId)
    }

    override suspend fun checkoutBasket(products: List<BasketProduct>) {
        purchaseHistoryDao.insert(PurchaseHistoryEntity(products, ZonedDateTime.now()))
        basketDao.deleteAll()
    }
}