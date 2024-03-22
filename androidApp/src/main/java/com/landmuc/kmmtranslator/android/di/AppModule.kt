package com.landmuc.kmmtranslator.android.di

import android.app.Application
import app.cash.sqldelight.db.SqlDriver
import com.landmuc.TranslateDatabase
import com.landmuc.kmmtranslator.translate.data.history.SqlDelightHistoryDataSource
import com.landmuc.kmmtranslator.translate.data.local.DatabaseDriverFactory
import com.landmuc.kmmtranslator.translate.data.remote.HttpClientFactory
import com.landmuc.kmmtranslator.translate.data.translate.KtorTranslateClient
import com.landmuc.kmmtranslator.translate.domain.history.HistoryDataSource
import com.landmuc.kmmtranslator.translate.domain.translate.Translate
import com.landmuc.kmmtranslator.translate.domain.translate.TranslateClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClientFactory().create()
    }

    @Provides
    @Singleton
    fun provideTranslateClient(httpClient: HttpClient): TranslateClient {
        return KtorTranslateClient(httpClient)
    }

    @Provides
    @Singleton
    fun provideDatabaseDriver(app: Application): SqlDriver {
        return DatabaseDriverFactory(app).create()
    }

    @Provides
    @Singleton
    fun provideHistoryDataSource(driver: SqlDriver): HistoryDataSource {
        return SqlDelightHistoryDataSource(TranslateDatabase(driver))
    }

    @Provides
    @Singleton
    fun provideTranslateUseCase(
        client: TranslateClient,
        dataSource: HistoryDataSource
    ): Translate {
        return Translate(client, dataSource)
    }
}