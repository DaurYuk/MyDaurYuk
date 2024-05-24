package com.example.mycapstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import com.example.mycapstone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val requestCameraLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // bottom bar navigation
        binding.bottomNavigationView.setOnNavigationItemReselectedListener { item ->
            when(item.itemId){
                R.id.navigation_feature1 -> {
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