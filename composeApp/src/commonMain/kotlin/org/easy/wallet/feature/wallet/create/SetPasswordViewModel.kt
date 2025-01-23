package org.easy.wallet.feature.wallet.create

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private const val VALID_OTP_CODE = "141414"

class SetPasswordViewModel : ViewModel() {
  private val _state = MutableStateFlow(PasswordUiState())
  val state = _state.asStateFlow()

  fun onAction(action: PasswordAction) {
    when (action) {
      is PasswordAction.OnChangeFieldFocused -> {
        _state.update {
          it.copy(
            focusedIndex = action.index
          )
        }
      }

      is PasswordAction.OnEnterNumber -> {
        enterNumber(action.number, action.index)
      }

      PasswordAction.OnKeyboardBack -> {
        val previousIndex = getPreviousFocusedIndex(state.value.focusedIndex)
        _state.update {
          it.copy(
            code = it.code.mapIndexed { index, number ->
              if (index == previousIndex) {
                null
              } else {
                number
              }
            },
            focusedIndex = previousIndex
          )
        }
      }
      PasswordAction.OnNext -> Unit
    }
  }

  private fun enterNumber(number: Int?, index: Int) {
    val newCode = state.value.code.mapIndexed { currentIndex, currentNumber ->
      if (currentIndex == index) {
        number
      } else {
        currentNumber
      }
    }
    val wasNumberRemoved = number == null
    _state.update {
      it.copy(
        code = newCode,
        focusedIndex = if (wasNumberRemoved || it.code.getOrNull(index) != null) {
          it.focusedIndex
        } else {
          getNextFocusedTextFieldIndex(
            currentCode = it.code,
            currentFocusedIndex = it.focusedIndex
          )
        },
        isValid = if (newCode.none { it == null }) {
          newCode.joinToString("") == VALID_OTP_CODE
        } else {
          null
        }
      )
    }
  }

  private fun getPreviousFocusedIndex(currentIndex: Int?): Int? = currentIndex?.minus(1)?.coerceAtLeast(0)

  private fun getNextFocusedTextFieldIndex(currentCode: List<Int?>, currentFocusedIndex: Int?): Int? {
    if (currentFocusedIndex == null) {
      return null
    }

    if (currentFocusedIndex == 5) {
      return currentFocusedIndex
    }

    return getFirstEmptyFieldIndexAfterFocusedIndex(
      code = currentCode,
      currentFocusedIndex = currentFocusedIndex
    )
  }

  private fun getFirstEmptyFieldIndexAfterFocusedIndex(code: List<Int?>, currentFocusedIndex: Int): Int {
    code.forEachIndexed { index, number ->
      if (index <= currentFocusedIndex) {
        return@forEachIndexed
      }
      if (number == null) {
        return index
      }
    }
    return currentFocusedIndex
  }
}

data class PasswordUiState(
  val code: List<Int?> = (1..6).map { null },
  val focusedIndex: Int? = null,
  val isValid: Boolean? = null
)

sealed interface PasswordAction {
  data class OnEnterNumber(
    val number: Int?,
    val index: Int
  ) : PasswordAction

  data class OnChangeFieldFocused(
    val index: Int
  ) : PasswordAction

  data object OnKeyboardBack : PasswordAction

  data object OnNext : PasswordAction
}