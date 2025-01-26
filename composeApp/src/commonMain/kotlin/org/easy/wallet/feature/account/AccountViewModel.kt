package org.easy.wallet.feature.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.easy.wallet.data.repository.WalletRepository

class AccountViewModel(
  private val walletRepository: WalletRepository
) : ViewModel() {
  val state = walletRepository.walletName().flatMapLatest { walletName ->
    walletRepository.activeWallet(walletName).map { mnemonic ->
      if (mnemonic.isNullOrBlank()) AccountUiState.NoSetup
      else AccountUiState.Info(mnemonic, walletName).also { println("===== $it") }
    }
  }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(3_000), AccountUiState.NoSetup)
}


sealed interface AccountUiState {
  data object NoSetup : AccountUiState
  data class Info(
    val mnemonic: String? = null,
    val walletName: String? = null
  ) : AccountUiState
}