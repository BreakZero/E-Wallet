package com.easy.wallet.shared.data.repository.transactions

import app.cash.paging.PagingSource
import app.cash.paging.PagingState
import com.easy.wallet.model.asset.AssetCoin
import com.easy.wallet.shared.data.repository.platform.OnChainRepository
import com.easy.wallet.shared.model.transaction.TransactionUiModel

internal const val TRANSACTION_PAGER_LIMIT = 20

internal class TransactionPagingSource(
  private val assetCoin: AssetCoin,
  private val onChainRepository: OnChainRepository
) : PagingSource<Int, TransactionUiModel>() {
  override fun getRefreshKey(state: PagingState<Int, TransactionUiModel>): Int? = state.anchorPosition?.let { anchorPosition ->
    state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
      ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
  }

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TransactionUiModel> = try {
    val currPage = params.key ?: 1
    val transactions = onChainRepository.loadTransactions(
      assetCoin,
      currPage,
      TRANSACTION_PAGER_LIMIT
    )
    LoadResult.Page(
      data = transactions,
      prevKey = if (currPage <= 1) null else currPage - 1,
      nextKey = if (transactions.isEmpty()) null else currPage + 1
    )
  } catch (e: Exception) {
    LoadResult.Error(e)
  }
}
