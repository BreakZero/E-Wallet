package org.easy.wallet.data.repository

import kotlinx.coroutines.flow.Flow
import org.easy.wallet.model.News

interface NewsRepository {
  fun getNews(): Flow<List<News>>
}