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
import kr.rmsxo.data.deseralizer.BaseModelDeserializer
import kr.rmsxo.domain.model.BaseModel
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : MainRepository {
    override fun getModelList(): Flow<List<BaseModel>> = flow {
        val inputStream = context.assets.open("product_list.json")
        val inputStreamReader = InputStreamReader(inputStream)
        val jsonString = inputStreamReader.readText()
        val type = object : TypeToken<List<BaseModel>>() {}.type

        emit(
            GsonBuilder()
                .registerTypeAdapter(BaseModel::class.java, BaseModelDeserializer)
                .create()
                .fromJson(jsonString, type)
        )
    }
}