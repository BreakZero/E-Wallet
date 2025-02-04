package com.easy.wallet.database.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOneOrNull
import com.easy.wallet.database.AssetPlatformEntityQueries
import com.easy.wallet.database.model.EvmNetworkInformation
import com.easy.wallet.database.model.asPublish
import com.easy.wallet.model.asset.AssetPlatform
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.easy.wallet.database.AssetPlatform as Entity

internal class AssetPlatformDaoImpl(
  private val queries: AssetPlatformEntityQueries,
  private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : AssetPlatformDao {
  override fun findAllStream(): Flow<List<AssetPlatform>> = queries
    .findAll()
    .asFlow()
    .mapToList(dispatcher)
    .map { it.map(Entity::toPublish) }

  override fun findByIdStream(platformId: String): Flow<AssetPlatform?> =
    queries.findById(platformId).asFlow().mapToOneOrNull(dispatcher).map {
      it?.toPublish()
    }

  override suspend fun insert(vararg assetPlatforms: Entity) {
    queries.transaction {
      assetPlatforms.onEach(queries::insertFullObject)
    }
  }
}

private fun Entity.toPublish(): AssetPlatform {
  val networkInfo = EvmNetworkInformation.decodeFromString(this.evm_network_info)?.asPublish()

  return AssetPlatform(
    id = id,
    shortName = short_name,
    chainIdentifier = chain_identifier,
    network = networkInfo
  )
}
