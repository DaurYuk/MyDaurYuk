package com.example.mycapstone.reward

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.example.mycapstone.R
import com.example.mycapstone.databinding.ActivityRewardBinding
import com.example.mycapstone.reward.adapter.WasteHistoryAdapter
import com.example.mycapstone.reward.repository.WasteRepository
import com.example.mycapstone.reward.viewmodel.WasteViewModel

class RewardActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRewardBinding
    private val wasteViewModel : WasteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRewardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = WasteHistoryAdapter()
        binding.recyclerView.adapter = adapter

        wasteViewModel.wasteHistory.observe(this){wasteHistory ->
            adapter.submitList(wasteHistory)
        }

        wasteViewModel.loadWasteHistory()

        binding.claimPointsButton.setOnClickListener {
            wasteViewModel.loadRewards()
            // Here you can show a dialog or new activity with the reward list
        }
    }
}