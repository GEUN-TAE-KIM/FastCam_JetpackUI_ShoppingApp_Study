package kr.rmsxo.domain.model

abstract class BaseModel {
    abstract val type : ModelType
}

enum class ModelType {
    PRODUCT,
    BANNER,
    BANNER_LIST,
}