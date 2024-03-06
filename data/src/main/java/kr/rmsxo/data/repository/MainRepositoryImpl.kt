package kr.rmsxo.data.repository

import kr.rmsxo.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kr.rmsxo.data.datasource.ProductDataSource
import kr.rmsxo.domain.model.BaseModel
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val dataSource: ProductDataSource
) : MainRepository {
    override fun getModelList(): Flow<List<BaseModel>> {
        return dataSource.getHomeComponents()
    }
}