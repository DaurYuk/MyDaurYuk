package com.example.mycapstone.history.db

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.mycapstone.history.HistoryPagingSource

class HistoryViewModel(private val dao: HistoryDao) : ViewModel() {
    val historyFlow = Pager(PagingConfig(pageSize = 20)) {
        HistoryPagingSource(dao)
    }.flow.cachedIn(viewModelScope)
}

