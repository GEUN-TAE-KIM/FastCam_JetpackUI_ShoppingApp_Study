package kr.rmsxo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.rmsxo.domain.model.TempModel
import kr.rmsxo.domain.usecase.TempUseCase
import javax.inject.Inject

@HiltViewModel
class TempViewModel @Inject constructor(private val useCase: TempUseCase) : ViewModel() {

    fun getTempModel() : TempModel {
        return useCase.getTempModel()
    }
}