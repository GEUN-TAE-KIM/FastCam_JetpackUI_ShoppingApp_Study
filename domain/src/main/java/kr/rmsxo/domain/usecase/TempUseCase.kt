package kr.rmsxo.domain.usecase

import kr.rmsxo.domain.model.TempModel
import kr.rmsxo.domain.repository.TempRepository
import javax.inject.Inject

class TempUseCase @Inject constructor(private val repository: TempRepository) {

    fun getTempModel() : TempModel {
        return repository.getTestData()
    }
}