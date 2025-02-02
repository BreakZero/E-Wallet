package org.easy.wallet.data.repository

import org.easy.wallet.network.source.EtherScanController

class EthereumBalanceRepository internal constructor(
  private val etherScanController: EtherScanController
): BalanceRepository {
  override suspend fun fetchBalance(address: String, contractAddress: String?): String {
    return etherScanController.balance(address, contractAddress)
  }
}