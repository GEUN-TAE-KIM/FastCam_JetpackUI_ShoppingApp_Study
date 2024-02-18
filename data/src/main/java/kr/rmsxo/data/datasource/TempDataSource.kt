package kr.rmsxo.data.datasource

import kr.rmsxo.domain.model.TempModel
import javax.inject.Inject

class TempDataSource @Inject constructor() {

    fun getTempModel() : TempModel {
        return TempModel("testModel")
    }
}