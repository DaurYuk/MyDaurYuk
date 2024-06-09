package com.example.mycapstone.reward.db

data class WasteItem (
    val id: String,
    val type: String,
    val date: String
)

data class Reward(
    val id: String,
    val name: String,
    val pointRequired: Int,
    val isEcoFriendly: Boolean
)