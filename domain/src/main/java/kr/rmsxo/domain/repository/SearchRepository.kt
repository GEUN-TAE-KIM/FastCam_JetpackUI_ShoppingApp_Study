package kr.rmsxo.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.rmsxo.domain.model.Product
import kr.rmsxo.domain.model.SearchKeyword

interface SearchRepository {

    suspend fun search(keyWord: SearchKeyword) : Flow<List<Product>>

    fun getSearchKeywords() : Flow<List<SearchKeyword>>
}