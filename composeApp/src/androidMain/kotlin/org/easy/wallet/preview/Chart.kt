package org.easy.wallet.preview

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.easy.wallet.components.NumberInputField

@Preview
@Composable
private fun OtpInputFieldPreview() {
  NumberInputField(
    number = null,
    focusRequester = remember { FocusRequester() },
    onFocusChanged = {},
    onKeyboardBack = {},
    onNumberChanged = {},
    modifier = Modifier
      .size(100.dp)
  )
}