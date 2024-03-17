package com.landmuc.kmmtranslator.translate.data.history

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.landmuc.TranslateDatabase
import com.landmuc.kmmtranslator.core.domain.util.CommonFlow
import com.landmuc.kmmtranslator.core.domain.util.toCommonFlow
import com.landmuc.kmmtranslator.translate.domain.history.HistoryDataSource
import com.landmuc.kmmtranslator.translate.domain.history.HistoryItem
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock

class SqlDelightHistoryDataSource(
    db: TranslateDatabase
): HistoryDataSource {

    private val queries = db.translateQueries

    override suspend fun getHistory(): CommonFlow<List<HistoryItem>> {
        return queries
            .getHistory()
            .asFlow()
            .mapToList(currentCoroutineContext())
            .map { history ->
                history.map { it.toHistoryItem() }
            }
            .toCommonFlow()
    }

    override suspend fun insertHistoryItem(item: HistoryItem) {
        queries.insertHistoryEntity(
            id = item.id,
            fromLanguageCode = item.fromLanguageCode,
            fromText = item.fromText,
            toLanguageCode = item.toLanguageCode,
            toText = item.toText,
            timestamp = Clock.System.now().toEpochMilliseconds()
        )
    }
}