package org.easy.wallet.feature.account

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import easywallet.composeapp.generated.resources.Res
import easywallet.composeapp.generated.resources.tab_account
import org.jetbrains.compose.resources.stringResource

@Composable
fun AccountScreen(
  navigateToWallet: () -> Unit
) {
  AccountTabScreen(onEvent = navigateToWallet)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AccountTabScreen(
  onEvent: () -> Unit
) {
  Scaffold(modifier = Modifier.fillMaxSize(),
    topBar = {
      TopAppBar(title = {
        Text(stringResource(Res.string.tab_account), style = MaterialTheme.typography.titleLarge)
      })
    }) {
    Column(
      modifier = Modifier.fillMaxSize().padding(it)
    ) {
      ListItem(
        modifier = Modifier.fillMaxWidth()
          .clickable(onClick = onEvent),
        headlineContent = {
          Text("Wallet Manager")
        }
      )
    }
  }
}