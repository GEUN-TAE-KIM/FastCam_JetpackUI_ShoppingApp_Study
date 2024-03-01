package kr.rmsxo.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.rmsxo.domain.model.BaseModel

interface MainRepository {
    fun getModelList() : Flow<List<BaseModel>>

}