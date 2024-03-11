package kr.rmsxo.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.rmsxo.domain.model.BaseModel
import kr.rmsxo.domain.model.Product

interface MainRepository {

    fun getModelList() : Flow<List<BaseModel>>

    suspend fun likeProduct(product: Product)

}