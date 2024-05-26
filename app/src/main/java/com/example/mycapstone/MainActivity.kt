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
    private lateinit var binding : ActivityMainBinding
    private val requestCameraLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
            }
        }

    // Buat instance dari NewsRepository
    private val newsRepository = NewsRepository.getInstance(
        ApiService.create(),
        NewsDatabase.getInstance(this).newsDao()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // bottom bar navigation
        binding.bottomNavigationView.setOnNavigationItemReselectedListener { item ->
            when(item.itemId){
                R.id.navigation_feature1 -> {
                    // Panggil fungsi untuk mendapatkan headline news
                    newsRepository.getHeadlineNews().observe(this, { result ->
                        // Handle hasil pemanggilan di sini
                    })
                    true
                }
                R.id.navigation_feature2 -> {
                    true
                }
                R.id.navigation_feature3 -> {
                    true
                }
                else -> false
            }
        }
    }

    private fun startCameraForScanning(){
        val camereIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        requestCameraLauncher.launch(camereIntent)
    }
}