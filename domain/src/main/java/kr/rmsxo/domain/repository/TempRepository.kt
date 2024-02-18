package kr.rmsxo.domain.repository

import kr.rmsxo.domain.model.TempModel

interface TempRepository {
    fun getTestData() : TempModel
}