package kr.rmsxo.presentation.model

import kr.rmsxo.domain.model.BaseModel

sealed class PresentationVM<T : BaseModel>(val model: T) {

}