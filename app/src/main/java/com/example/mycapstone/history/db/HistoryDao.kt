package com.example.mycapstone.history.db;

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface HistoryDao {

    @Insert
    suspend fun insert(history: History)

    @Query("SELECT * FROM history ORDER BY id DESC LIMIT :limit OFFSET :offset")
    suspend fun getPagedHistory(limit: Int, offset: Int): List<History>

    @Delete
    suspend fun deleteHistory(history: History)
}