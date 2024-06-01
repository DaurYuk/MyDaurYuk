package com.example.mycapstone.recomendation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mycapstone.databinding.ActivityRecomendationBinding

class RecommendationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecomendationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecomendationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}