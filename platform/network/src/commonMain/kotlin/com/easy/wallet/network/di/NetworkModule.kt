package com.easy.wallet.network.di

import com.easy.wallet.network.httpClient
import com.easy.wallet.network.source.blockchair.BlockchairApi
import com.easy.wallet.network.source.blockchair.BlockchairDataSource
import com.easy.wallet.network.source.opensea.OpenseaApi
import com.easy.wallet.network.source.opensea.OpenseaDataSource
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module

enum class SourceQualifier {
    OPENSEA, BLOCK_CHAIR, ETHER_SCAN
}

private fun httpClientWithDefault(special: HttpClientConfig<*>.() -> Unit = {}): HttpClient {
    return httpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                },
            )
        }
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.BODY
        }
        special()
    }
}

val networkModule = module {
    single(qualifier = named(name = SourceQualifier.BLOCK_CHAIR.name)) {
        httpClientWithDefault {
            defaultRequest {
                url("https://api.blockchair.com/")
                header("accept", "application/json")
            }
        }
    }
    single(qualifier = named(name = SourceQualifier.OPENSEA.name)) {
        httpClientWithDefault {
            defaultRequest {
                url("https://api.opensea.io/v2/")
                header("accept", "application/json")
                // TODO move to backend, delegate forward
                header("X-API-KEY", "")
            }
        }
    }
    single<BlockchairApi> { BlockchairDataSource(get(qualifier = named(SourceQualifier.BLOCK_CHAIR.name))) }
    single<OpenseaApi> { OpenseaDataSource(get(qualifier = named(SourceQualifier.OPENSEA.name))) }
}
