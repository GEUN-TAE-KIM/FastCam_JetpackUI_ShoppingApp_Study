package kr.rmsxo.data.datasource

import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.rmsxo.data.deseralizer.BaseModelDeserializer
import kr.rmsxo.domain.model.BaseModel
import kr.rmsxo.domain.model.Product
import java.io.InputStreamReader
import javax.inject.Inject

class ProductDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getProducts(): Flow<List<BaseModel>> = flow {
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