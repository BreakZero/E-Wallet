package org.easy.wallet.feature.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.easy.wallet.data.repository.NewsRepository

class NewsViewModel constructor(private val newsRepository: NewsRepository) : ViewModel() {
  init {
    newsRepository.getNews().onEach {
      println("===== $it")
    }.launchIn(viewModelScope)
  }
}