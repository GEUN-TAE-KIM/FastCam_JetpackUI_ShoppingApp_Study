package kr.rmsxo.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kr.rmsxo.data.datasource.PreferenceDatasource
import kr.rmsxo.domain.model.AccountInfo
import kr.rmsxo.domain.repository.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val preferenceDatasource: PreferenceDatasource
) : AccountRepository {

    private val accountInfoFlow = MutableStateFlow(preferenceDatasource.getAccountInfo())

    override fun getAccountInfo(): StateFlow<AccountInfo?> {
        return accountInfoFlow
    }

    override suspend fun signIn(accountInfo: AccountInfo) {
        preferenceDatasource.putAccountInfo(accountInfo)
        accountInfoFlow.emit(accountInfo)
    }

    override suspend fun signOut() {
        preferenceDatasource.removeAccountInfo()
        accountInfoFlow.emit(null)
    }
}