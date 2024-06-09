package com.example.mycapstone.reward.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mycapstone.reward.db.Reward
import com.example.mycapstone.reward.db.WasteItem
import com.example.mycapstone.reward.repository.WasteRepository

class WasteViewModel: ViewModel() {
    private val repository = WasteRepository()

    private val _wasteHistory = MutableLiveData<List<WasteItem>>()
    val wasteHistory: LiveData<List<WasteItem>> get() = _wasteHistory

    private val _rewards = MutableLiveData<List<Reward>>()
    val rewards : LiveData<List<Reward>> get() = _rewards

    private val _ecoFriendlyRewards = MutableLiveData<List<Reward>>()
    val ecoFriendlyRewards: LiveData<List<Reward>> get() = _ecoFriendlyRewards

    fun loadWasteHistory(){
        _wasteHistory.value = repository.getWasteHistory()
    }

    fun loadRewards(){
        _rewards.value = repository.getRewards()
    }

    fun loadEcoFriendlyRewards(){
        _ecoFriendlyRewards.value = repository.getEcoFriendly()
    }

    fun addWasteItem(wasteItem: WasteItem){
        repository.addWasteItem(wasteItem)
        loadWasteHistory()
    }
}