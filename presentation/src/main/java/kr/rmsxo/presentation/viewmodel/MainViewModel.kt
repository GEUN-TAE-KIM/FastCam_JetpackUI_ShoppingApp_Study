package kr.rmsxo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewMode @Inject constructor() : ViewModel() {

    fun openSearchForm() {
        println("dsds")

    }

}