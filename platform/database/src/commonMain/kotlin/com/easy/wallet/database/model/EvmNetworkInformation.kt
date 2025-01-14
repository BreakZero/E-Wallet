package com.easy.wallet.database.model

import com.easy.wallet.model.asset.AssetNetwork
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

private val JSON = Json

@Serializable
internal data class EvmNetworkInformation(
  val networkName: String,
  val rpcUrl: String,
  val explorerUrl: String?
) {
  companion object {
    internal fun decodeFromString(value: String?): EvmNetworkInformation? {
      if (value == null) return null
      return try {
        JSON.decodeFromString<EvmNetworkInformation>(value)
      } catch (e: Exception) {
        null
      }
    }
  }
}

internal fun EvmNetworkInformation.encodeToString(): String = JSON.encodeToString(EvmNetworkInformation.serializer(), this)

internal fun EvmNetworkInformation.asPublish(): AssetNetwork = AssetNetwork(
  networkName = networkName,
  rpcUrl = rpcUrl,
  explorerUrl = explorerUrl
)
