package org.easy.wallet.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.easy.wallet.model.News
import org.easy.wallet.network.source.BlockChairController

class NewsRepositoryImpl internal constructor(
  private val blockChairController: BlockChairController
) : NewsRepository {
  override fun getNews(): Flow<List<News>> {
    return flow {
      blockChairController.getNews(20, 0).also {
        emit(it.map { blockChairNewDto -> News(blockChairNewDto.hash) })
      }
    }
  }
}