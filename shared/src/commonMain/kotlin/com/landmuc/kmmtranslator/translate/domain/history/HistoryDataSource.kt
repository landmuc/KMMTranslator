package com.landmuc.kmmtranslator.translate.domain.history

import com.landmuc.kmmtranslator.core.domain.util.CommonFlow

interface HistoryDataSource {
    suspend fun getHistory(): CommonFlow<List<HistoryItem>>
    suspend fun insertHistoryItem(item: HistoryItem)
}