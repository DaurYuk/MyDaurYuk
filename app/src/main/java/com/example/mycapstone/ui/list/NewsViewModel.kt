package com.example.mycapstone.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.newsapp.data.local.entity.NewsEntity
import com.example.mycapstone.data.NewsRepository
import com.example.mycapstone.data.Result

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    private val _newsList = MutableLiveData<List<NewsEntity>?>()
    val newsList: MutableLiveData<List<NewsEntity>?> get() = _newsList

    private val _errorState = MutableLiveData<Boolean>()
    val errorState: LiveData<Boolean> get() = _errorState

    fun fetchNews() {
        _errorState.value = false // Reset error state
        newsRepository.getHeadlineNews().observeForever { result ->
            when (result) {
                is Result.Success -> _newsList.value = result.data
                is Result.Error -> _errorState.value = true
                is Result.Loading -> { /* Handle loading state if necessary */ }
            }
        }
    }

    fun retryFetchNews() {
        fetchNews() // Retry fetching news
    }
}