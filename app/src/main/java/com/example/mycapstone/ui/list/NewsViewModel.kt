package com.example.mycapstone.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mycapstone.data.NewsRepository
import com.example.mycapstone.data.local.entity.NewsEntity
import com.example.mycapstone.data.Result

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    private val _newsList = MutableLiveData<Result<List<NewsEntity>>>()
    val newsList: LiveData<Result<List<NewsEntity>>> get() = _newsList

    fun fetchNews() {
        _newsList.value = Result.Loading // Emit loading state
        newsRepository.getHeadlineNews().observeForever { result ->
            _newsList.value = result
        }
    }
}