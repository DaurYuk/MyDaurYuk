package com.example.mycapstone.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mycapstone.R
import com.example.mycapstone.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}