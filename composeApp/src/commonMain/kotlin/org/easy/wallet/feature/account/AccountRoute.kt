package ai.askquin.feature.account

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun AccountScreen() {
  AccountTabScreen()
}

@Composable
private fun AccountTabScreen() {
  Scaffold(modifier = Modifier.fillMaxSize()) {
    Box(
      modifier = Modifier.fillMaxSize().padding(it),
      contentAlignment = Alignment.Center
    ) {
      Text("Account")
    }
  }
}