package ai.askquin.di

import ai.askquin.data.di.dataModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
  appDeclaration()
  modules(
    viewModelModule,
    dataModule
  )
}