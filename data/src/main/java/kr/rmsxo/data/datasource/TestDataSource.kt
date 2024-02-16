package kr.rmsxo.data.datasource

import kr.rmsxo.data.model.TestModelResponse

class TestDataSource {

    fun getTestModelResponse() : TestModelResponse {
        return TestModelResponse("response")
    }
}