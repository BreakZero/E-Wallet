package com.easy.wallet.home.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.easy.wallet.design.component.DynamicAsyncImage
import com.easy.wallet.model.token.TokenWithBalance

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TokenItemView(
    modifier: Modifier = Modifier,
    tokenWithBalance: TokenWithBalance
) {
    val token = tokenWithBalance.token
    val balance = tokenWithBalance.balance
    Card(
        modifier = modifier,
        onClick = { /*TODO*/ },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 12.dp,
                    vertical = 8.dp,
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            DynamicAsyncImage(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                imageUrl = token.logoURI,
                contentDescription = token.name,
            )
            Text(modifier = Modifier.padding(start = 12.dp), text = token.name)
            Spacer(modifier = Modifier.weight(1.0f))
            Text(text = "$balance ${token.symbol}")
        }
    }
}
