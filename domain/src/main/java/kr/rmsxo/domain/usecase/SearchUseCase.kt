package kr.rmsxo.domain.usecase

import kotlinx.coroutines.flow.Flow
import kr.rmsxo.domain.model.Product
import kr.rmsxo.domain.model.SearchKeyword
import kr.rmsxo.domain.repository.SearchRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend fun search(keyword: SearchKeyword): Flow<List<Product>> {
        return searchRepository.search(keyword)
    }

    fun getSearchKeywords() : Flow<List<SearchKeyword>> {
        return searchRepository.getSearchKeywords()
    }
}