package com.landmuc.kmmtranslator.translate.data.remote

import io.ktor.client.HttpClient

expect class HttpClientFactory {
    fun create(): HttpClient
}