package com.example.mycapstone.register_login.api

import com.example.mycapstone.register_login.viewmodel.LoginDataAccount
import com.example.mycapstone.register_login.viewmodel.RegisterDataAccount
import com.example.mycapstone.register_login.viewmodel.ResponseDetail
import com.example.mycapstone.register_login.viewmodel.ResponseLogin
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("user/register")
    suspend fun register(@Body data: RegisterDataAccount): Response<ResponseDetail>

    @POST("user/login")
    suspend fun login(@Body data: LoginDataAccount): Response<ResponseLogin>
}