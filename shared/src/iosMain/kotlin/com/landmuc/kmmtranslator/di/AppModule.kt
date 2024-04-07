package com.landmuc.kmmtranslator.di

import com.landmuc.TranslateDatabase
import com.landmuc.kmmtranslator.translate.data.history.SqlDelightHistoryDataSource
import com.landmuc.kmmtranslator.translate.data.local.DatabaseDriverFactory
import com.landmuc.kmmtranslator.translate.data.remote.HttpClientFactory
import com.landmuc.kmmtranslator.translate.data.translate.KtorTranslateClient
import com.landmuc.kmmtranslator.translate.domain.history.HistoryDataSource
import com.landmuc.kmmtranslator.translate.domain.translate.Translate
import com.landmuc.kmmtranslator.translate.domain.translate.TranslateClient
import com.landmuc.kmmtranslator.voice_to_text.domain.VoiceToTextParser

interface AppModule {
    val historyDataSource: HistoryDataSource
    val client: TranslateClient
    val translateUseCase: Translate
    val voiceParser: VoiceToTextParser
}


class AppModuleImpl(
    parser: VoiceToTextParser
): AppModule {

    override val historyDataSource: HistoryDataSource by lazy {
        SqlDelightHistoryDataSource(
            TranslateDatabase(
                DatabaseDriverFactory().create()
            )
        )
    }

    override val client: TranslateClient by lazy {
        KtorTranslateClient(
            HttpClientFactory().create()
        )
    }

    override val translateUseCase: Translate by lazy {
        Translate(client, historyDataSource)
    }

    override val voiceParser = parser
}