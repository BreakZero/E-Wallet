package org.easy.wallet.data.repository

import kotlinx.coroutines.flow.Flow

interface WalletRepository {
  fun generateMnemonic(): String

  suspend fun saveWallet(name: String, value: String)

  fun walletName(): Flow<String?>
  fun activeWallet(walletName: String?): Flow<String?>
}