package org.easy.wallet.feature.wallet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun WalletOptionScreen(
  popBackStack: () -> Unit
) {
  WalletOptions(popBackStack = popBackStack)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WalletOptions(
  popBackStack: () -> Unit
) {
  Box(modifier = Modifier.fillMaxSize()) {
    ModalBottomSheet(onDismissRequest = popBackStack) {
      Column(
        modifier = Modifier.fillMaxWidth()
      ) {
        ListItem(
          modifier = Modifier.fillMaxWidth(),
          headlineContent = {
            Text("Create Wallet")
          }
        )
        ListItem(
          modifier = Modifier.fillMaxWidth(),
          headlineContent = {
            Text("Restore by Seed")
          }
        )
      }
    }
  }
}