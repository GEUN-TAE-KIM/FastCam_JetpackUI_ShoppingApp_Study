package kr.rmsxo.data.repository

import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.flow
import kr.rmsxo.domain.model.Product
import kr.rmsxo.domain.repository.MainRepository
import java.io.InputStreamReader
import kotlinx.coroutines.flow.Flow
import kr.rmsxo.data.datasource.ProductDataSource
import kr.rmsxo.data.deseralizer.BaseModelDeserializer
import kr.rmsxo.domain.model.BaseModel
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val dataSource: ProductDataSource
) : MainRepository {
    override fun getModelList(): Flow<List<BaseModel>> {
        return dataSource.getProducts()
    }
}