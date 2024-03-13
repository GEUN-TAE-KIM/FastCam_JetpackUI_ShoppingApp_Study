package kr.rmsxo.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.rmsxo.domain.model.PurchaseHistory

interface PurchaseHistoryRepository {
    fun getPurchaseHistory(): Flow<List<PurchaseHistory>>
}