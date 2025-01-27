package org.easy.wallet.feature.assets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AssetsScreen() {
  val viewModel: AssetsViewModel = koinViewModel()
  val state by viewModel.state.collectAsStateWithLifecycle()
  AssetsScreen(state)
}

@Composable
private fun AssetsScreen(state: AssetsUiState) {
  Scaffold(modifier = Modifier.fillMaxSize()) {
    Column(modifier = Modifier.fillMaxSize().padding(it)) {
      when (state) {
        AssetsUiState.Fetching -> {}
        AssetsUiState.None -> {}
        is AssetsUiState.WalletAssets -> {
          Text(text = state.walletName)
          LazyColumn(modifier = Modifier.fillMaxWidth().weight(1f)) {
            items(state.assets) {
              Text(modifier = Modifier.fillMaxWidth().height(48.dp), text = it.coinName)
            }
          }
        }
      }
    }
  }
}