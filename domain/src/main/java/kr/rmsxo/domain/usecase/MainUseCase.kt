package kr.rmsxo.domain.usecase

import kotlinx.coroutines.flow.Flow
import kr.rmsxo.domain.model.BaseModel
import kr.rmsxo.domain.repository.MainRepository
import javax.inject.Inject

class MainUseCase @Inject constructor(private val mainRepository: MainRepository) {

    fun getModelList() : Flow<List<BaseModel>> {
        return mainRepository.getModelList()
    }
}