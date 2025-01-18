package org.easy.wallet.network

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.request
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpMethod

internal expect fun httpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient

internal suspend inline fun <reified T> HttpClient.safeGet(
  urlString: String,
  noinline requestBuilder: HttpRequestBuilder.() -> Unit = {}
): Result<T> = safeRequest(HttpMethod.Get, urlString, requestBuilder)

internal suspend inline fun <reified T> HttpClient.safePost(
  urlString: String,
  noinline requestBuilder: HttpRequestBuilder.() -> Unit = {}
): Result<T> = safeRequest(HttpMethod.Post, urlString, requestBuilder)

private suspend inline fun <reified T> HttpClient.safeRequest(
  method: HttpMethod,
  urlString: String,
  noinline requestBuilder: HttpRequestBuilder.() -> Unit = {}
): Result<T> = try {
  request(urlString) {
    this.method = method
    requestBuilder()
  }.body<T>().let {
    Result.success(it)
  }
} catch (exception: ResponseException) {
  exception.debugLogging()
  Result.failure(exception)
} catch (e: Exception) {
  Result.failure(e)
}

internal suspend inline fun <reified T> HttpClient.tryGet(
  urlString: String,
  isThrows: Boolean = false,
  block: HttpRequestBuilder.() -> Unit = {}
): T? = try {
  get(urlString, block).body<T>()
} catch (exception: ResponseException) {
  exception.debugLogging()
  if (isThrows) throw exception else null
} catch (e: Exception) {
  if (isThrows) throw e else null
}

internal suspend inline fun <reified T> HttpClient.tryPost(
  urlString: String = "",
  isThrows: Boolean = false,
  block: HttpRequestBuilder.() -> Unit = {}
): T? = try {
  post(urlString, block).body<T>()
} catch (exception: ResponseException) {
  exception.debugLogging()
  if (isThrows) throw exception else null
} catch (e: Exception) {
  if (isThrows) throw e else null
}

private suspend fun ResponseException.debugLogging() {
  val responseBody = try {
    response.bodyAsText()
  } catch (e: Exception) {
    "Error reading response body: ${e.message}"
  }

  when (this) {
    is RedirectResponseException -> {
      // Status codes 3XX
      println("===== Redirect Response (3XX): ${response.status} - Body: $responseBody")
    }

    is ClientRequestException -> {
      // status codes 4XX
      println("===== Client Request Error (4XX): ${response.status} - Body: $responseBody")
    }

    is ServerResponseException -> {
      // status codes 5XX
      println("===== Server Error (5XX): ${response.status} - Body: $responseBody")
    }

    else -> {
      println("===== Unknown Response Exception: ${response.status} - Body: $responseBody")
    }
  }
}