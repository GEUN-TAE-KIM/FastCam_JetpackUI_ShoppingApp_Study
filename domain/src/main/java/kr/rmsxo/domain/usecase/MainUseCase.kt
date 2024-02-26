package kr.rmsxo.domain.usecase

import kr.rmsxo.domain.model.Product
import kr.rmsxo.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainUseCase @Inject constructor(private val mainRepository: MainRepository) {

    fun getProductList() : Flow<List<Product>> {
        return mainRepository.getProductList()
    }
}