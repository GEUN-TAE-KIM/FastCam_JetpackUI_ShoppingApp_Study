package kr.rmsxo.domain.usecase

import kotlinx.coroutines.flow.Flow
import kr.rmsxo.domain.model.Product
import kr.rmsxo.domain.repository.LikeRepository
import javax.inject.Inject

class LikeUseCase @Inject constructor(
    private val repository: LikeRepository
) {
    fun getLikeProduct(): Flow<List<Product>> {
        return repository.getLikeProduct()
    }
}