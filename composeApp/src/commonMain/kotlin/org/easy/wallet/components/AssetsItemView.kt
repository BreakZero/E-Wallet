package org.easy.wallet.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.easy.wallet.model.Assets
import org.easy.wallet.model.Balance

@Composable
fun AssetsItemView(
  assets: Assets,
  modifier: Modifier = Modifier,
  onItemClick: () -> Unit
) {
  Row(
    modifier = modifier
      .clickable(onClick = onItemClick)
      .padding(
        horizontal = 16.dp,
        vertical = 12.dp
      ),
    verticalAlignment = Alignment.CenterVertically
  ) {
    DynamicAsyncImage(
      modifier = Modifier
        .size(48.dp)
        .clip(CircleShape),
      imageUrl = assets.logoUrl,
      contentDescription = assets.coinName
    )
    Text(
      modifier = Modifier.padding(start = 12.dp),
      text = assets.coinName,
      style = MaterialTheme.typography.titleMedium
    )
    Spacer(modifier = Modifier.weight(1.0f))
    val balance = if (assets is Balance) assets.balance else 0.0
    Text(
      text = "$balance ${assets.symbol}",
      style = MaterialTheme.typography.titleLarge
    )
  }
}