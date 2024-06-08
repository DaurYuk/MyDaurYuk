package com.example.mycapstone.reward

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mycapstone.R
import com.example.mycapstone.databinding.ActivityRewardBinding

class RewardActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRewardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRewardBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}