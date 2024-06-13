package com.example.mycapstone.news.data.di

import android.content.Context
import com.example.mycapstone.news.data.NewsRepository
import com.example.mycapstone.news.data.local.room.NewsDatabase
import com.example.mycapstone.news.data.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): NewsRepository {
        val apiService = ApiConfig.getApiService()
        val database = NewsDatabase.getInstance(context)
        val dao = database.newsDao()
        return NewsRepository.getInstance(apiService, dao)
    }
}