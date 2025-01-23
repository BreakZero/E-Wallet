package org.easy.wallet.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey

object PreferencesKeys {
  val LOGIN_STATE = booleanPreferencesKey("login_state")
  val WALLET_NAME_KEY = stringSetPreferencesKey("wallet_names")
}