package com.example.mycapstone.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mycapstone.data.local.entity.NewsEntity

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveNews(news: NewsEntity)

    @Query("DELETE FROM news WHERE title = :newsTitle")
    suspend fun deleteNews(newsTitle: String)
}