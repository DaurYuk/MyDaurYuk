package com.example.mycapstone.history.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class History(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val imagePath: String,
    val result: String,
    val confidenceScore: Double
)
