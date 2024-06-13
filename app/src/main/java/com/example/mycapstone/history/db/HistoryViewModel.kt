package com.example.mycapstone.history.db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mycapstone.history.HistoryPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HistoryViewModel(private val dao: HistoryDao) : ViewModel() {
    val historyFlow: Flow<PagingData<History>> = Pager(PagingConfig(pageSize = 20)) {
        HistoryPagingSource(dao)
    }.flow.cachedIn(viewModelScope)

    fun deleteHistory(history: History) {
        viewModelScope.launch {
            dao.deleteHistory(history)
        }
    }
}

