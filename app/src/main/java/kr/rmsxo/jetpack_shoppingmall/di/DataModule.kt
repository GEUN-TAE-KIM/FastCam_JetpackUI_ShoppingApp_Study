package kr.rmsxo.jetpack_shoppingmall.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.rmsxo.data.repository.AccountRepositoryImpl
import kr.rmsxo.data.repository.BasketRepositoryImpl
import kr.rmsxo.data.repository.CategoryRepositoryImpl
import kr.rmsxo.data.repository.LikeRepositoryImpl
import kr.rmsxo.data.repository.MainRepositoryImpl
import kr.rmsxo.data.repository.ProductDetailRepositoryImpl
import kr.rmsxo.data.repository.SearchRepositoryImpl
import kr.rmsxo.data.repository.TempRepositoryImpl
import kr.rmsxo.domain.repository.AccountRepository
import kr.rmsxo.domain.repository.BasketRepository
import kr.rmsxo.domain.repository.CategoryRepository
import kr.rmsxo.domain.repository.LikeRepository
import kr.rmsxo.domain.repository.MainRepository
import kr.rmsxo.domain.repository.ProductDetailRepository
import kr.rmsxo.domain.repository.SearchRepository
import kr.rmsxo.domain.repository.TempRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindTempRepository(tempRepositoryImpl: TempRepositoryImpl): TempRepository

    @Binds
    @Singleton
    fun bindMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository

    @Binds
    @Singleton
    fun bindCategoryRepository(categoryRepositoryImpl: CategoryRepositoryImpl): CategoryRepository

    @Binds
    @Singleton
    fun bindProductDetailRepository(productDetailRepositoryImpl: ProductDetailRepositoryImpl): ProductDetailRepository

    @Binds
    @Singleton
    fun bindSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository

    @Binds
    @Singleton
    fun bindAccountRepository(accountRepositoryImpl: AccountRepositoryImpl): AccountRepository

    @Binds
    @Singleton
    fun bindLikeRepository(likeRepositoryImpl: LikeRepositoryImpl): LikeRepository

    @Binds
    @Singleton
    fun bindBasketRepository(basketRepositoryImpl: BasketRepositoryImpl): BasketRepository
}