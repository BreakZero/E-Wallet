package org.easy.wallet.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import org.easy.wallet.model.News
import org.easy.wallet.network.source.BlockChairController

internal class NewsPagingSource(
  private val blockChairController: BlockChairController
) : PagingSource<Int, News>() {
  companion object {
    internal const val PAGE_SIZE = 20
  }

  override fun getRefreshKey(state: PagingState<Int, News>): Int? = null

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News> {
    val offset = params.key ?: 0
    val newsList = blockChairController.getNews(limit = PAGE_SIZE, offset = offset).map {
      News(it.hash)
    }
    return LoadResult.Page(
      data = newsList,
      prevKey = if (offset <= 0) null else offset - PAGE_SIZE,
      nextKey = if (newsList.isEmpty()) null else offset + PAGE_SIZE
    )
  }
}