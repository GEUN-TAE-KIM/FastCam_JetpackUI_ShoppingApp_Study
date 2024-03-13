package kr.rmsxo.domain.usecase

import kotlinx.coroutines.flow.Flow
import kr.rmsxo.domain.model.PurchaseHistory
import kr.rmsxo.domain.repository.PurchaseHistoryRepository
import javax.inject.Inject

class PurchaseHistoryUseCase @Inject constructor(
    private val repository: PurchaseHistoryRepository
) {

    fun getPurchaseHistory() : Flow<List<PurchaseHistory>> {
        return repository.getPurchaseHistory()
    }
}