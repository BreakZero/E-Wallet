package org.easy.wallet.network.di

import co.touchlab.kermit.Logger
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import org.easy.wallet.network.httpClient
import org.koin.dsl.module

private fun httpClientWithDefault(serializersModule: SerializersModule? = null, special: HttpClientConfig<*>.() -> Unit = {}): HttpClient =
  httpClient {
    install(HttpCookies)
    install(ContentNegotiation) {
      json(
        Json {
          prettyPrint = true
          isLenient = true
          ignoreUnknownKeys = true
          allowStructuredMapKeys = true
          serializersModule?.let {
            this.serializersModule = it
          }
        }
      )
    }
    install(Logging) {
      level = LogLevel.BODY
      logger = object : io.ktor.client.plugins.logging.Logger {
        override fun log(message: String) {
          Logger.d { message }
        }
      }
    }
    special()
  }

val networkModule = module {
  single {
    httpClientWithDefault {
      defaultRequest {
        url {
          protocol = URLProtocol.HTTPS
          host = "staging.askquin.cn"
          path("/")
        }
        header("Content-Type", "application/json")
      }
    }
  }
}