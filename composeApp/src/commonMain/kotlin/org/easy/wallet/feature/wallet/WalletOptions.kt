package org.easy.wallet.feature.wallet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@Composable
fun WalletOptionScreen(
  onCreateWallet: () -> Unit,
  onRestoreWallet: () -> Unit,
  popBackStack: () -> Unit
) {
  WalletOptions(
    onCreateWallet = onCreateWallet,
    onRestoreWallet = onRestoreWallet,
    popBackStack = popBackStack
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WalletOptions(
  onCreateWallet: () -> Unit,
  onRestoreWallet: () -> Unit,
  popBackStack: () -> Unit
) {
  val sheetState = rememberModalBottomSheetState()
  val scope = rememberCoroutineScope()
  Box(modifier = Modifier.fillMaxSize()) {
    ModalBottomSheet(sheetState = sheetState, onDismissRequest = popBackStack) {
      Column(
        modifier = Modifier.fillMaxWidth()
      ) {
        ListItem(
          modifier = Modifier.fillMaxWidth().clickable {
            scope
              .launch {
                sheetState.hide()
              }.invokeOnCompletion {
                onCreateWallet()
              }
          },
          headlineContent = {
            Text("Create Wallet")
          }
        )
        ListItem(
          modifier = Modifier.fillMaxWidth().clickable {
            scope
              .launch {
                sheetState.hide()
              }.invokeOnCompletion {
                onRestoreWallet()
              }
          },
          headlineContent = {
            Text("Restore by Seed")
          }
        )
      }
    }
  }
}