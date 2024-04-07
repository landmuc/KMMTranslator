package com.landmuc.kmmtranslator.testing

import com.landmuc.kmmtranslator.core.domain.util.CommonFlow
import com.landmuc.kmmtranslator.core.domain.util.toCommonFlow
import com.landmuc.kmmtranslator.translate.domain.history.HistoryDataSource
import com.landmuc.kmmtranslator.translate.domain.history.HistoryItem
import kotlinx.coroutines.flow.MutableStateFlow

class FakeHistoryDataSource: HistoryDataSource {

    private val _data = MutableStateFlow<List<HistoryItem>>(emptyList())
    override suspend fun getHistory(): CommonFlow<List<HistoryItem>> {
        return _data.toCommonFlow()
    }

    override suspend fun insertHistoryItem(item: HistoryItem) {
        _data.value += item
    }
}