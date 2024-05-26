package com.example.mycapstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import com.example.mycapstone.data.NewsRepository
import com.example.mycapstone.data.local.room.NewsDatabase
import com.example.mycapstone.data.remote.retrofit.ApiService
import com.example.mycapstone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set initial fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, NewsFragment())
                .commit()
        }

        // Bottom bar navigation
        binding.bottomNavigationView.setOnNavigationItemReselectedListener { item ->
            when (item.itemId) {
                R.id.navigation_feature1 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, NewsFragment())
                        .commit()
                    true
                }
                R.id.navigation_feature2 -> {
                    // Replace with other fragment if needed
                    true
                }
                R.id.navigation_feature3 -> {
                    // Replace with other fragment if needed
                    true
                }
                else -> false
            }
        }
    }
}
