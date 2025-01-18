package org.easy.wallet

import androidx.compose.ui.window.ComposeUIViewController
import org.easy.wallet.di.initKoin
import org.easy.wallet.ui.EasyWalletApp
import org.easy.wallet.ui.rememberAppState

fun mainViewController() = ComposeUIViewController(
  configure = {
    initKoin()
  }
) {
  val appState = rememberAppState()
  EasyWalletApp(appState)
}