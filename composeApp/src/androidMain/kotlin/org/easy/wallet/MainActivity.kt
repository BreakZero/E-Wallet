package org.easy.wallet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.easy.wallet.ui.EasyWalletApp
import org.easy.wallet.ui.rememberAppState

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      val appState = rememberAppState()
      EasyWalletApp(appState)
    }
  }
}