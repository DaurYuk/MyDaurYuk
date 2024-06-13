package com.example.mycapstone.news.ui.detail

import androidx.lifecycle.*
import com.example.mycapstone.news.data.NewsRepository
import com.example.mycapstone.news.data.local.entity.NewsEntity

class NewsDetailViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    private val newsData = MutableLiveData<NewsEntity>()

    fun setNewsData(news: NewsEntity) {
        newsData.value = news
    }
}