package com.landmuc.kmmtranslator.di

import com.landmuc.TranslateDatabase
import com.landmuc.kmmtranslator.translate.data.history.SqlDelightHistoryDataSource
import com.landmuc.kmmtranslator.translate.data.local.DatabaseDriverFactory
import com.landmuc.kmmtranslator.translate.data.remote.HttpClientFactory
import com.landmuc.kmmtranslator.translate.data.translate.KtorTranslateClient
import com.landmuc.kmmtranslator.translate.domain.history.HistoryDataSource
import com.landmuc.kmmtranslator.translate.domain.translate.Translate
import com.landmuc.kmmtranslator.translate.domain.translate.TranslateClient

class AppModule {

    val historyDataSource: HistoryDataSource by lazy {
        SqlDelightHistoryDataSource(
            TranslateDatabase(
                DatabaseDriverFactory().create()
            )
        )
    }

    private val translateClient: TranslateClient by lazy {
        KtorTranslateClient(
            HttpClientFactory().create()
        )
    }

    val translateUseCase: Translate by lazy {
        Translate(translateClient, historyDataSource)
    }
}