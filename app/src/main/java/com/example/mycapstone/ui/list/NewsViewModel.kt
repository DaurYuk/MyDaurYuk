package com.example.mycapstone.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycapstone.data.NewsRepository
import com.example.mycapstone.data.local.entity.NewsEntity
import com.example.mycapstone.data.Result
import kotlinx.coroutines.launch

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    private val _newsList = MutableLiveData<Result<List<NewsEntity>>>()
    val newsList: LiveData<Result<List<NewsEntity>>> get() = _newsList

    private val _errorState = MutableLiveData<Boolean>()
    val errorState: LiveData<Boolean> get() = _errorState

    fun fetchNews() {
        _newsList.value = Result.Loading // Emit loading state
        viewModelScope.launch {
            newsRepository.getHeadlineNews().observeForever { result ->
                _newsList.value = result
                if (result is Result.Error) {
                    _errorState.value = true
                } else {
                    _errorState.value = false
                }
            }
        }
    }
}