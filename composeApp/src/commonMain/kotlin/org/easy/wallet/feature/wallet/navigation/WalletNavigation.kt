package org.easy.wallet.feature.wallet.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.easy.wallet.feature.wallet.WalletOptionScreen

@Serializable
data object WalletOptionRoute

fun NavGraphBuilder.attachWalletRoutes(
  popBackStack: () -> Unit
) {
  composable<WalletOptionRoute> {
    WalletOptionScreen(popBackStack = popBackStack)
  }
}