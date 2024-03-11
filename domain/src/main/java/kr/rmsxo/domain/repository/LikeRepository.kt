package kr.rmsxo.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.rmsxo.domain.model.Product

interface LikeRepository {

    fun getLikeProduct() : Flow<List<Product>>
}