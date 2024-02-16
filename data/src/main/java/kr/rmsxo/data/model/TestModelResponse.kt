package kr.rmsxo.data.model

import kr.rmsxo.domain.model.TestModel

class TestModelResponse(val name: String?)

fun TestModelResponse.toDomainModel() : TestModel? {
    if (name != null) {
        return TestModel(name)
    }
    return null
}