package com.example.mycapstone.history

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mycapstone.history.db.History
import com.example.mycapstone.history.db.HistoryDao

class HistoryPagingSource(private val dao: HistoryDao) : PagingSource<Int, History>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, History> {
        // Example of adding logs
        Log.d("HistoryActivity", "Submitting data to adapter")
        return try {
            val nextPageNumber = params.key ?: 0
            val response = dao.getPagedHistory(params.loadSize, nextPageNumber * params.loadSize)
            LoadResult.Page(
                data = response,
                prevKey = if (nextPageNumber == 0) null else nextPageNumber - 1,
                nextKey = if (response.isEmpty()) null else nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, History>): Int? {
        // Example of adding logs
        Log.d("HistoryActivity", "Submitting data to adapter")

        return state.anchorPosition?.let { anchorPosition ->
            // Example of adding logs
            Log.d("HistoryActivity", "Submitting data to adapter")
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
