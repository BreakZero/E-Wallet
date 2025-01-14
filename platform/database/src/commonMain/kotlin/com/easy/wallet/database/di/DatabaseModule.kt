package com.easy.wallet.database.di

import com.easy.wallet.database.SharedDatabase
import com.easy.wallet.database.dao.AssetPlatformDao
import com.easy.wallet.database.dao.AssetPlatformDaoImpl
import com.easy.wallet.database.dao.CoinDao
import com.easy.wallet.database.dao.CoinDaoImpl
import com.easy.wallet.database.dao.WalletDao
import com.easy.wallet.database.dao.WalletDaoImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal expect fun factoryModule(): Module

val databaseModule = module {
  includes(factoryModule())
  singleOf(::SharedDatabase)

  single {
    AssetPlatformDaoImpl(
      get<SharedDatabase>().database.assetPlatformEntityQueries,
      dispatcher = Dispatchers.IO
    )
  } bind AssetPlatformDao::class

  single {
    CoinDaoImpl(
      get<SharedDatabase>().database.coinEntityQueries,
      dispatcher = Dispatchers.IO
    )
  } bind CoinDao::class

  single {
    WalletDaoImpl(
      get<SharedDatabase>().database.walletQueries,
      dispatcher = Dispatchers.IO
    )
  } bind WalletDao::class
}
