package kr.rmsxo.presentation.viewmodel.purchase_history

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.rmsxo.domain.usecase.PurchaseHistoryUseCase
import javax.inject.Inject

@HiltViewModel
class PurchaseHistoryViewModel @Inject constructor(
    private val useCase: PurchaseHistoryUseCase
) : ViewModel() {

    val purchaseHistory = useCase.getPurchaseHistory()

}