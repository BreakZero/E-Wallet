package com.easy.wallet.shared.di

import com.easy.wallet.core.di.dispatcherModule
import com.easy.wallet.database.di.databaseModule
import com.easy.wallet.datastore.di.userDefaultModule
import com.easy.wallet.network.di.networkModule
import com.easy.wallet.shared.data.multiwallet.MultiWalletRepository
import com.easy.wallet.shared.data.repository.news.NewsRepository
import com.easy.wallet.shared.domain.CoinTrendUseCase
import com.easy.wallet.shared.domain.DashboardUseCase
import com.easy.wallet.shared.domain.TokenAmountUseCase
import com.easy.wallet.shared.domain.TransactionPagerUseCase
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}): KoinApplication {
    return startKoin {
        appDeclaration()
        modules(sharedModule)
        modules(dispatcherModule)
        modules(userDefaultModule())
        modules(networkModule)
        modules(databaseModule)
    }
}

//fun KoinApplication.Companion.start(): KoinApplication = initKoin {}
//
//val Koin.dashboardUseCase: DashboardUseCase
//    get() = get()
//
//val Koin.coinTrendUseCase: CoinTrendUseCase
//    get() = get()
//
//val Koin.tokenAmountUseCase: TokenAmountUseCase
//    get() = get()
//
//val Koin.transactionPagerUseCase: TransactionPagerUseCase
//    get() = get()
//
//val Koin.multiWalletRepository: MultiWalletRepository
//    get() = get()
//
//val Koin.newsRepository: NewsRepository
//    get() = get()
