package kr.rmsxo.domain.repository

import kotlinx.coroutines.flow.StateFlow
import kr.rmsxo.domain.model.AccountInfo

interface AccountRepository {

    fun getAccountInfo() : StateFlow<AccountInfo?>

    suspend fun signIn(accountInfo: AccountInfo)

    suspend fun signOut()
}