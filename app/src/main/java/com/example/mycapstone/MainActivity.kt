package com.example.mycapstone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mycapstone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        try {
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
        } catch (e: Exception) {
            // Tangani kesalahan saat menginflasi layout
            e.printStackTrace()
            Log.e("MainActivity", "Error inflating layout: ${e.message}")
        }

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
