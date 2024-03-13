package kr.rmsxo.domain.usecase

import kotlinx.coroutines.flow.Flow
import kr.rmsxo.domain.model.Category
import kr.rmsxo.domain.model.Product
import kr.rmsxo.domain.repository.CategoryRepository
import javax.inject.Inject

class CategoryUseCase @Inject constructor(private val repository: CategoryRepository) {

    fun getCategories() : Flow<List<Category>> {
        return repository.getCategories()
    }
    fun getProductsByCategory(category: Category) : Flow<List<Product>> {
        return repository.getProductsByCategory(category)
    }

    suspend fun likeProduct(product: Product) {
        repository.likeProduct(product)
    }
}