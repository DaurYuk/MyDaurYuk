package com.example.mycapstone.reward

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.mycapstone.databinding.ActivityRewardBinding
import com.example.mycapstone.reward.adapter.RewardAdapter
import com.example.mycapstone.reward.viewmodel.WasteViewModel

class RewardActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRewardBinding
    private val wasteViewModel : WasteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRewardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rewardAdapter = RewardAdapter()
        binding.recyclerView.adapter = rewardAdapter

        wasteViewModel.totalPoints.observe(this){points ->
            binding.totalPointsTextView.text = "Total Points: $points"
            wasteViewModel.loadClaimableRewards(points)
        }

        wasteViewModel.claimableRewards.observe(this) { rewards ->
            rewardAdapter.submitList(rewards)
        }

        wasteViewModel.apiResponse.observe(this){response ->
            Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
        }

        wasteViewModel.loadWasteHistory()

        binding.claimPointsButton.setOnClickListener {
//            val rewardId = "someRewardId"
//            wasteViewModel.claimReward(rewardId)
//            wasteViewModel.loadRewards()
            // Here you can show a dialog or new activity with the reward list :please help me
        }
    }
}