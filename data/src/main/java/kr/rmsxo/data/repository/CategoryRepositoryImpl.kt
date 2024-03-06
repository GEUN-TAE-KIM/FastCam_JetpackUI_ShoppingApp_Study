package kr.rmsxo.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kr.rmsxo.data.datasource.ProductDataSource
import kr.rmsxo.domain.model.Category
import kr.rmsxo.domain.model.Product
import kr.rmsxo.domain.repository.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val dataSource: ProductDataSource
) : CategoryRepository {
    override fun getCategories(): Flow<List<Category>> = flow {
        emit(
            listOf(
                Category.Top, Category.Bag, Category.Outerwear,
                Category.Dress, Category.FashionAccessories, Category.Pants,
                Category.Skirt, Category.Shoes
            )
        )
    }

    override fun getProductsByCategory(category: Category): Flow<List<Product>> {
        return dataSource.getHomeComponents().map { list ->
            list.filterIsInstance<Product>().filter { product ->
                product.category.categoryId == category.categoryId
            }
        }

    }
}