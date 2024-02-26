package kr.rmsxo.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.rmsxo.domain.model.Product

interface MainRepository {
    fun getProductList() : Flow<List<Product>>

}