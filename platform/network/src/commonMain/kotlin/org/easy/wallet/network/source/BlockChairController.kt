package org.easy.wallet.network.source

import io.ktor.client.HttpClient
import io.ktor.client.request.parameter
import org.easy.wallet.network.model.dto.BlockChairBaseResponse
import org.easy.wallet.network.model.dto.BlockChairNewDto
import org.easy.wallet.network.safeGet

class BlockChairController internal constructor(
  private val httpClient: HttpClient
) {
  suspend fun getNews(limit: Int, offset: Int): List<BlockChairNewDto> {
    val result =
      httpClient.safeGet<BlockChairBaseResponse<List<BlockChairNewDto>>>(urlString = "news?q=language(en)") {
        parameter("limit", limit)
        parameter("offset", offset)
      }
    return result.getOrNull()?.data ?: emptyList()
  }
}