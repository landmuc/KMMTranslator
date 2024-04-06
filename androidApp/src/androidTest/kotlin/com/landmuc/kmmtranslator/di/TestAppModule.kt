package com.landmuc.kmmtranslator.di

import com.landmuc.kmmtranslator.translate.data.local.FakeHistoryDataSource
import com.landmuc.kmmtranslator.translate.data.remote.FakeTranslateClient
import com.landmuc.kmmtranslator.translate.domain.history.HistoryDataSource
import com.landmuc.kmmtranslator.translate.domain.translate.Translate
import com.landmuc.kmmtranslator.translate.domain.translate.TranslateClient
import com.landmuc.kmmtranslator.voice_to_text.data.FakeVoiceToTextParser
import com.landmuc.kmmtranslator.voice_to_text.domain.VoiceToTextParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TestAppModule {

    @Provides
    @Singleton
    fun provideFakeHistoryDataSource(): HistoryDataSource {
        return FakeHistoryDataSource()
    }

    @Provides
    @Singleton
    fun provideFakeTranslateClient(): TranslateClient {
        return FakeTranslateClient()
    }

    @Provides
    @Singleton
    fun provideTranslateUseCase(
        client: TranslateClient,
        dataSource: HistoryDataSource
    ): Translate {
        return Translate(
            client = client ,
            historyDataSource = dataSource
        )
    }

   @Provides
   @Singleton
   fun providesFakeVoiceToTextParser(): VoiceToTextParser {
       return FakeVoiceToTextParser()
   }

}