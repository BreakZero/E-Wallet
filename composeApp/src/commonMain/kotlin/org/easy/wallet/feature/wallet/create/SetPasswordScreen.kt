package org.easy.wallet.feature.wallet.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import easywallet.composeapp.generated.resources.Res
import easywallet.composeapp.generated.resources.button_continue
import org.easy.wallet.components.NumberInputField
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SetPasswordScreen(onContinue: () -> Unit) {
  val viewModel: SetPasswordViewModel = koinViewModel()
  val state by viewModel.state.collectAsStateWithLifecycle()

  val focusRequesters = remember {
    List(state.code.size) { FocusRequester() }
  }
  val focusManager = LocalFocusManager.current
  val keyboardManager = LocalSoftwareKeyboardController.current

  LaunchedEffect(state.focusedIndex) {
    state.focusedIndex?.let { index ->
      focusRequesters.getOrNull(index)?.requestFocus()
    }
  }

  LaunchedEffect(state.code, keyboardManager) {
    val allNumbersEntered = state.code.none { it == null }
    if (allNumbersEntered) {
      focusRequesters.forEach {
        it.freeFocus()
      }
      focusManager.clearFocus()
      keyboardManager?.hide()
    }
  }

  SetPasswordScreen(
    state = state,
    focusRequesters = focusRequesters,
    onAction = { action ->
      when (action) {
        is PasswordAction.OnEnterNumber -> {
          if (action.number != null) {
            focusRequesters[action.index].freeFocus()
          }
        }

        is PasswordAction.OnNext -> {
          onContinue()
        }

        else -> Unit
      }
      viewModel.onAction(action)
    }
  )
}

@Composable
private fun SetPasswordScreen(
  state: PasswordUiState,
  focusRequesters: List<FocusRequester>,
  onAction: (PasswordAction) -> Unit
) {
  Scaffold(
    modifier = Modifier.fillMaxSize(),
    bottomBar = {
      Button(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp)
          .padding(bottom = 16.dp)
          .navigationBarsPadding(),
        onClick = {
          onAction(PasswordAction.OnNext)
        }
      ) {
        Text(text = stringResource(Res.string.button_continue))
      }
    }
  ) {
    Row(
      modifier = Modifier
        .fillMaxSize()
        .padding(it)
        .padding(16.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
    ) {
      state.code.forEachIndexed { index, number ->
        NumberInputField(
          number = number,
          focusRequester = focusRequesters[index],
          onFocusChanged = { isFocused ->
            if (isFocused) {
              onAction(PasswordAction.OnChangeFieldFocused(index))
            }
          },
          onNumberChanged = { newNumber ->
            onAction(PasswordAction.OnEnterNumber(newNumber, index))
          },
          onKeyboardBack = {
            onAction(PasswordAction.OnKeyboardBack)
          },
          modifier = Modifier
            .weight(1f)
            .aspectRatio(1f)
        )
      }
    }
  }
}