package kr.rmsxo.domain.usecase

import kotlinx.coroutines.flow.StateFlow
import kr.rmsxo.domain.model.AccountInfo
import kr.rmsxo.domain.repository.AccountRepository
import javax.inject.Inject

class AccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {

    fun getAccountInfo(): StateFlow<AccountInfo?> {
        return accountRepository.getAccountInfo()
    }

    suspend fun signIn(accountInfo: AccountInfo) {
        accountRepository.signInGoogle(accountInfo)
    }

    suspend fun signOut() {
        accountRepository.signOutGoogle()
    }
}