package com.example.mycapstone.news.data

import com.example.mycapstone.BuildConfig
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.mycapstone.news.data.local.entity.NewsEntity
import com.example.mycapstone.news.data.local.room.NewsDao
import com.example.mycapstone.news.data.remote.retrofit.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class NewsRepository(
    private val apiService: ApiService,
    private val newsDao: NewsDao
) {
    fun getHeadlineNews(): LiveData<Result<List<NewsEntity>>> = liveData(Dispatchers.IO) {
        emit(Result.Loading)
        try {
            val response = apiService.getNews(BuildConfig.API_KEY)
            val articles = response.articles
            val newsList = articles.map { article ->
                NewsEntity(
                    article.title,
                    article.publishedAt,
                    article.urlToImage,
                    article.url
                )
            }
            emit(Result.Success(newsList))
        } catch (e: Exception) {
            Log.e("NewsRepository", "getHeadlineNews: ${e.message}")
            emit(Result.Error(e.message ?: "An unknown error occurred"))
        }
    }

    suspend fun saveNews(news: NewsEntity) {
        withContext(Dispatchers.IO) {
            newsDao.saveNews(news)
        }
    }

    suspend fun deleteNews(title: String) {
        withContext(Dispatchers.IO) {
            newsDao.deleteNews(title)
        }
    }

    companion object {
        @Volatile
        private var instance: NewsRepository? = null
        fun getInstance(
            apiService: ApiService,
            newsDao: NewsDao
        ): NewsRepository =
            instance ?: synchronized(this) {
                instance ?: NewsRepository(apiService, newsDao)
            }.also { instance = it }
    }
}