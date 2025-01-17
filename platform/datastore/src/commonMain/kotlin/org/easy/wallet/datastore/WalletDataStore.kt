package org.easy.wallet.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okio.Path.Companion.toPath

internal const val DATA_STORE_FILE_NAME = "wallet.preferences_pb"

class WalletDataStore internal constructor(
  private val dataStore: DataStore<Preferences>
) {
  fun <T> flowOf(key: Preferences.Key<T>, default: T): Flow<T> = dataStore.data.map { it[key] ?: default }

  suspend fun <T> update(key: Preferences.Key<T>, value: T) {
    dataStore.edit { it[key] = value }
  }
}

internal fun createDataStore(producePath: () -> String): DataStore<Preferences> = PreferenceDataStoreFactory.createWithPath(
  produceFile = { producePath().toPath() }
)