package kr.rmsxo.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kr.rmsxo.data.db.dao.PurchaseHistoryDao
import kr.rmsxo.data.db.entity.toDomainModel
import kr.rmsxo.domain.model.PurchaseHistory
import kr.rmsxo.domain.repository.PurchaseHistoryRepository
import javax.inject.Inject

class PurchaseHistoryRepositoryImpl @Inject constructor(
    private val purchaseHistoryDao: PurchaseHistoryDao
)  : PurchaseHistoryRepository {
    override fun getPurchaseHistory(): Flow<List<PurchaseHistory>> {
        return purchaseHistoryDao.getAll().map { list ->
            list.map { it.toDomainModel() }
        }
    }
}