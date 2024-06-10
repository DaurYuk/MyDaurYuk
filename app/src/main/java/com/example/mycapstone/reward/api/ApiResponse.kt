package com.example.mycapstone.reward.api

data class ClaimRewardResponse(
    val status: String,
    val statusCode: Int,
    val message: String
)

data class UpdateRewardBalanceResponse(
    val status: String,
    val statusCode: Int,
    val message: String,
    val rewards_balance: Int
)