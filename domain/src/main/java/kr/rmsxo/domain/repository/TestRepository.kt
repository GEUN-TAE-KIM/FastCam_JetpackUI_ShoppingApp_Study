package kr.rmsxo.domain.repository

import kr.rmsxo.domain.model.TestModel

interface TestRepository {
    fun getTestData() : TestModel
}