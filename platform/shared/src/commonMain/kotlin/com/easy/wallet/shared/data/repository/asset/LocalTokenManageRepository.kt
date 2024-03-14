package com.easy.wallet.shared.data.repository.asset

import com.easy.wallet.database.dao.LocalTokenDao
import com.easy.wallet.model.TokenInformation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocalTokenManageRepository(
    private val localTokenDao: LocalTokenDao
) : TokenManageRepository {
    override suspend fun addOne(
        id: String,
        chainId: Long,
        name: String,
        symbol: String,
        decimal: Int,
        contractAddress: String?,
        iconUri: String,
        isActive: Boolean
    ) {
        localTokenDao.addOne(
            id, chainId, name, symbol, decimal, contractAddress, iconUri, isActive
        )
    }

    override suspend fun deleteById(id: String) {
        TODO("Not yet implemented")
    }

    override fun allTokens(): Flow<List<TokenInformation>> {
        return flow {
            emit(localTokenDao.allTokens())
        }
    }
}
