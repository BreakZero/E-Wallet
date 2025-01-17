package org.easy.wallet.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey

object PreferencesKeys {
  val LOGIN_STATE = booleanPreferencesKey("login_state")
}