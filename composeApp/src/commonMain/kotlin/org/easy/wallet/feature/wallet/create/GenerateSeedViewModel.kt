package org.easy.wallet.feature.wallet.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.easy.wallet.data.repository.WalletRepository

class GenerateSeedViewModel internal constructor(
  private val walletRepository: WalletRepository
) : ViewModel() {
  private val mnemonic = walletRepository.generateMnemonic()
  val words = mnemonic.split(" ")

  fun onAction() {
    viewModelScope.launch {
      walletRepository.saveWallet("wallet1", mnemonic)
    }
  }
}