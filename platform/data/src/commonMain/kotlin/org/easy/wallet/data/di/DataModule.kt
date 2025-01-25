package org.easy.wallet.data.di

import org.easy.wallet.data.paging.NewsPagingSource
import org.easy.wallet.data.repository.NewsRepository
import org.easy.wallet.data.repository.NewsRepositoryImpl
import org.easy.wallet.data.repository.WalletRepository
import org.easy.wallet.data.repository.WalletRepositoryImpl
import org.easy.wallet.datastore.di.storeModules
import org.easy.wallet.network.di.networkModule
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {

  includes(networkModule, storeModules)

  single { NewsRepositoryImpl(get()) } bind NewsRepository::class
  single { WalletRepositoryImpl(get()) } bind WalletRepository::class

  factory { NewsPagingSource(get()) }
}