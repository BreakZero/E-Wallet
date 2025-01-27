package org.easy.wallet.feature.assets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import org.easy.wallet.data.repository.AssetsRepository
import org.easy.wallet.data.repository.WalletRepository
import org.easy.wallet.model.Assets

class AssetsViewModel(
  private val walletRepository: WalletRepository,
  private val assetsRepository: AssetsRepository
) : ViewModel() {
  val state = combine(
    walletRepository.activeWallet(""),
    assetsRepository.loadAllAssets()
  ) { mnemonic, assets ->
    if (mnemonic.isNullOrBlank()) {
      AssetsUiState.None
    } else {
      AssetsUiState.WalletAssets(
        walletName = "wallet1",
        assets = assets
      )
    }
  }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(3_000), AssetsUiState.Fetching)
}

sealed interface AssetsUiState {
  data object Fetching : AssetsUiState
  data object None : AssetsUiState
  data class WalletAssets(val walletName: String, val assets: List<Assets>) : AssetsUiState
}