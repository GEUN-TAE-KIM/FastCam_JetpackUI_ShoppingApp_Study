package kr.rmsxo.data.deseralizer

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import kr.rmsxo.domain.model.Banner
import kr.rmsxo.domain.model.BannerList
import kr.rmsxo.domain.model.BaseModel
import kr.rmsxo.domain.model.ModelType
import kr.rmsxo.domain.model.Product
import java.lang.reflect.Type

class BaseModelDeserializer : JsonDeserializer<BaseModel> {

    companion object {
        private const val TYPE = "type"
    }

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): BaseModel {
        val root = json?.asJsonObject
        val gson = GsonBuilder().create()

        val typeString = root?.get(TYPE)?.asString ?: ""

        return when (ModelType.valueOf(typeString)) {
            ModelType.BANNER -> {
                gson.fromJson(root, Banner::class.java)
            }

            ModelType.PRODUCT -> {
                gson.fromJson(root, Product::class.java)
            }

            ModelType.BANNER_LIST -> {
                gson.fromJson(root, BannerList::class.java)
            }
        }
    }
}