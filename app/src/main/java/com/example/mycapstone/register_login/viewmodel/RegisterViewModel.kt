package com.example.mycapstone.register_login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycapstone.register_login.api.RetrofitClient
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    val apiService = RetrofitClient.apiService

    fun register(name: String, email: String, password: String, onResult: (ResponseDetail?) -> Unit) {
        viewModelScope.launch {
            val response = apiService.register(RegisterDataAccount(name, email, password))
            if (response.isSuccessful) {
                onResult(response.body())
            } else {
                onResult(null)
            }
        }
    }
}