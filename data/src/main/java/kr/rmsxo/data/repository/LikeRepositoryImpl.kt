package kr.rmsxo.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kr.rmsxo.data.db.dao.LikeDao
import kr.rmsxo.data.db.entity.toDomainModel
import kr.rmsxo.domain.model.Product
import kr.rmsxo.domain.repository.LikeRepository
import javax.inject.Inject

class LikeRepositoryImpl @Inject constructor(
    private val likeDao: LikeDao
) : LikeRepository {

    override fun getLikeProduct(): Flow<List<Product>> {
        return likeDao.getAll().map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

}