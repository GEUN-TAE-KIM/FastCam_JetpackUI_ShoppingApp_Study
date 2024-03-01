package kr.rmsxo.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.rmsxo.domain.model.Category
import kr.rmsxo.domain.model.Product

interface CategoryRepository {
    fun getCategories(): Flow<List<Category>>
    fun getProductsByCategory(category: Category): Flow<List<Product>>
}