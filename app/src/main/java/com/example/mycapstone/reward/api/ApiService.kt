package com.example.mycapstone.reward.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("reward/claim")
    fun claimReward(
        @Query("rewardId") rewardId: String
    ): Call<ClaimRewardResponse>

    @GET("reward-balance")
    fun updateRewardBalance(
        @Query("point") point: Int
    ): Call<UpdateRewardBalanceResponse>
}