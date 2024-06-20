package com.example.mycapstone.register_login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycapstone.register_login.api.RetrofitClient
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    val apiService = RetrofitClient.apiService

    fun login(email: String, password: String, onResult: (ResponseLogin?) -> Unit) {
        viewModelScope.launch {
            val response = apiService.login(LoginDataAccount(email, password))
            if (response.isSuccessful) {
                onResult(response.body())
            } else {
                onResult(null)
            }
        }
    }
}