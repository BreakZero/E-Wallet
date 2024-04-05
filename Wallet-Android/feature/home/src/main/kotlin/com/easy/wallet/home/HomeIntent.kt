package com.easy.wallet.home

import com.easy.wallet.model.TokenInformation
import com.easy.wallet.shared.model.TokenUiModel

internal sealed interface HomeEvent {
    data object CreateWallet : HomeEvent
    data object RestoreWallet : HomeEvent
    data object SettingsClicked : HomeEvent
    data class TokenClicked(val token: TokenInformation) : HomeEvent
}

internal sealed interface HomeUiState {
    data object Loading : HomeUiState

    data class WalletUiState(
        val tokens: List<TokenUiModel>
    ) : HomeUiState
}
