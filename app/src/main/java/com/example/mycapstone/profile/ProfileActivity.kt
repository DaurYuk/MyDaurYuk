package com.example.mycapstone.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mycapstone.LoginActivity
import com.example.mycapstone.databinding.ActivityProfileBinding
import com.example.mycapstone.reward.RewardActivity

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        logoutButton()
        rewardButton()
    }

    private fun logoutButton() {
        binding.logoutButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }

    private fun rewardButton() {
        binding.rewardButton.setOnClickListener {
            val pick = Intent(this, RewardActivity::class.java)
            startActivity(pick)
            finish()
        }
    }
}
