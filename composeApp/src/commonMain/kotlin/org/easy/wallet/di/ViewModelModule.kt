package org.easy.wallet.di

import org.easy.wallet.feature.news.NewsViewModel
import org.easy.wallet.feature.wallet.create.GenerateSeedViewModel
import org.easy.wallet.feature.wallet.create.SetPasswordViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
  viewModel { NewsViewModel(get()) }
  viewModel { SetPasswordViewModel() }
  viewModel { GenerateSeedViewModel(get()) }
}