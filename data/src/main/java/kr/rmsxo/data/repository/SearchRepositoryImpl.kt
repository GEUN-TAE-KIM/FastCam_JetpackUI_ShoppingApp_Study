package kr.rmsxo.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kr.rmsxo.data.datasource.ProductDataSource
import kr.rmsxo.data.db.dao.LikeDao
import kr.rmsxo.data.db.dao.SearchDao
import kr.rmsxo.data.db.entity.SearchKeywordEntity
import kr.rmsxo.data.db.entity.toDomain
import kr.rmsxo.domain.model.Product
import kr.rmsxo.domain.model.SearchFilter
import kr.rmsxo.domain.model.SearchKeyword
import kr.rmsxo.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val dataSource: ProductDataSource,
    private val searchDao: SearchDao,
    //  private val likeDao: LikeDao,
) : SearchRepository {

    override suspend fun search(searchKeyword: SearchKeyword, filters: List<SearchFilter>): Flow<List<Product>> {
        searchDao.insert(SearchKeywordEntity(searchKeyword.keyword))
        return dataSource.getProducts().map { list ->
            list.filter { isAvailableProduct(it, searchKeyword, filters) }
        }
    }

    private fun isAvailableProduct(product: Product, searchKeyword: SearchKeyword, filters: List<SearchFilter>): Boolean {
        var isAvailable = true
        filters.forEach { isAvailable = isAvailable && it.isAvailableProduct(product) }

        return isAvailable && product.productName.contains(searchKeyword.keyword)
    }


    override fun getSearchKeywords(): Flow<List<SearchKeyword>> {
        return searchDao.getAll().map { it.map { entity -> entity.toDomain() } }
    }

    /*    override suspend fun likeProduct(product: Product) {
            if(product.isLike) {
                likeDao.delete(product.productId)
            } else {
                likeDao.insert(product.toLikeProductEntity().copy(isLike = true))
            }
        }*/

    private fun updateLikeStatus(product: Product, likeProductIds: List<String>): Product {
        return product.copy(isLike = likeProductIds.contains(product.productId))
    }
}