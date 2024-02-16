package kr.rmsxo.data.repository

import kr.rmsxo.data.datasource.TempDataSource
import kr.rmsxo.domain.model.TempModel
import kr.rmsxo.domain.repository.TempRepository
import javax.inject.Inject

class TempRepositoryImpl @Inject constructor(private val dataSource: TempDataSource) : TempRepository {

    override fun getTestData(): TempModel {
        return dataSource.getTempModel()
    }
}