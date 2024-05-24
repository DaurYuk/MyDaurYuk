package com.example.mycapstone.ui.detail

import androidx.lifecycle.*
import com.example.mycapstone.data.NewsRepository
import com.dicoding.newsapp.data.local.entity.NewsEntity

class NewsDetailViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    private val newsData = MutableLiveData<NewsEntity>()

    fun setNewsData(news: NewsEntity) {
        newsData.value = news
    }
}