package kr.rmsxo.data.repository

import kr.rmsxo.data.datasource.TestDataSource
import kr.rmsxo.data.model.toDomainModel
import kr.rmsxo.domain.model.TestModel
import kr.rmsxo.domain.repository.TestRepository

class TestRepositoryImpl(val dataSource: TestDataSource) : TestRepository {

    override fun getTestData(): TestModel {
        return dataSource.getTestModelResponse().toDomainModel()
    }
}