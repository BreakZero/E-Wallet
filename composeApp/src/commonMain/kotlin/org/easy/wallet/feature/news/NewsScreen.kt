package org.easy.wallet.feature.news

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun NewsScreen() {
  NewsTabScreen()
}

@Composable
private fun NewsTabScreen() {
  Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color.Red) {
    Box(
      modifier = Modifier.fillMaxSize().padding(it),
      contentAlignment = Alignment.Center
    ) {
      Text("News")
    }
  }
}