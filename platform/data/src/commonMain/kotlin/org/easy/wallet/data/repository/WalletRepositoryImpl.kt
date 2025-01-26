package org.easy.wallet.data.repository

import co.touchlab.kermit.Logger
import com.trustwallet.core.HDWallet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import org.easy.wallet.datastore.WalletDataStore

class WalletRepositoryImpl internal constructor(
  private val walletDataStore: WalletDataStore
): WalletRepository {
  override fun generateMnemonic(): String {
    val hdWallet = HDWallet(128, "")
    return hdWallet.mnemonic
  }

  override suspend fun saveWallet(name: String, value: String) {
    Logger.d { "wallet name: $name, mnemonic: $value" }
    walletDataStore.addWallet(name, value)
  }

  override fun walletName(): Flow<String?> {
    return walletDataStore.getWalletName()
  }

  override fun activeWallet(walletName: String?): Flow<String?> {
    return walletDataStore.activeWallet(walletName.orEmpty()).catch { emit("") }
  }
}