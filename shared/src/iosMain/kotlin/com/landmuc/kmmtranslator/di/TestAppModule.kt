package com.landmuc.kmmtranslator.di

import com.landmuc.kmmtranslator.testing.FakeHistoryDataSource
import com.landmuc.kmmtranslator.testing.FakeTranslateClient
import com.landmuc.kmmtranslator.testing.FakeVoiceToTextParser
import com.landmuc.kmmtranslator.translate.domain.history.HistoryDataSource
import com.landmuc.kmmtranslator.translate.domain.translate.Translate
import com.landmuc.kmmtranslator.translate.domain.translate.TranslateClient
import com.landmuc.kmmtranslator.voice_to_text.domain.VoiceToTextParser

class TestAppModule: AppModule {
    override val historyDataSource: HistoryDataSource = FakeHistoryDataSource()
    override val client: TranslateClient = FakeTranslateClient()
    override val translateUseCase: Translate = Translate(
        client = client,
        historyDataSource = historyDataSource
    )
    override val voiceParser = FakeVoiceToTextParser()
}