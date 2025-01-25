package org.easy.wallet.feature.wallet.create

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import easywallet.composeapp.generated.resources.Res
import easywallet.composeapp.generated.resources.button_continue
import easywallet.composeapp.generated.resources.create_wallet_secure_write_down_seed
import easywallet.composeapp.generated.resources.create_wallet_secure_write_down_seed_desc
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GenerateSeedScreen() {
  val viewModel: GenerateSeedViewModel = koinViewModel()
  GenerateSeedScreen(
    words = viewModel.words,
    onAction = {
      viewModel.onAction()
    }
  )
}

@Composable
private fun GenerateSeedScreen(words: List<String>, onAction: () -> Unit) {
  var hiddenWords by remember { mutableStateOf(true) }
  Scaffold(
    modifier = Modifier.fillMaxSize(),
    bottomBar = {
      Button(
        modifier = Modifier
          .fillMaxWidth()
          .navigationBarsPadding()
          .padding(horizontal = 16.dp)
          .padding(bottom = 16.dp),
        enabled = !hiddenWords,
        onClick = onAction
      ) {
        Text(text = stringResource(Res.string.button_continue))
      }
    }
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(it)
        .padding(horizontal = 16.dp),
      verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
      Text(
        text = stringResource(Res.string.create_wallet_secure_write_down_seed),
        style = MaterialTheme.typography.headlineLarge
      )
      Text(text = stringResource(Res.string.create_wallet_secure_write_down_seed_desc))

      Box(
        modifier = Modifier
          .fillMaxWidth()
          .wrapContentHeight()
          .clip(RoundedCornerShape(12.dp))
      ) {
        LazyVerticalGrid(
          modifier = Modifier
            .fillMaxWidth()
            .let { modifier -> if (hiddenWords) modifier.blur(6.dp) else modifier },
          horizontalArrangement = Arrangement.spacedBy(16.dp),
          verticalArrangement = Arrangement.spacedBy(12.dp),
          columns = GridCells.Fixed(3),
          contentPadding = PaddingValues(12.dp)
        ) {
          items(words.size, key = { index: Int -> index }) { index ->
            Text(
              modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(6.dp))
                .background(MaterialTheme.colorScheme.tertiary)
                .padding(vertical = 8.dp),
              text = words[index],
              color = MaterialTheme.colorScheme.onTertiary,
              textAlign = TextAlign.Center
            )
          }
        }
        if (hiddenWords) {
          Column(
            modifier = Modifier
              .matchParentSize()
              .clickable {
                hiddenWords = !hiddenWords
              },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
          ) {
            Icon(imageVector = Icons.Default.Share, contentDescription = null)
            Text(text = "Tap to reveal your seed phrase")
          }
        }
      }
    }
  }
}