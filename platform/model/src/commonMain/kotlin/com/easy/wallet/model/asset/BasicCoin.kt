package com.easy.wallet.model.asset

data class BasicCoin(
  override val id: String,
  override val name: String,
  override val symbol: String,
  override val decimalPlace: Int,
  override val logoURI: String,
  override val contract: String?,
  override val platform: AssetPlatform
) : CoinModel
