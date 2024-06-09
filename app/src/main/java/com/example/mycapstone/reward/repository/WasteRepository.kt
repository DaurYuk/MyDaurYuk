package com.example.mycapstone.reward.repository

import com.example.mycapstone.reward.db.Reward
import com.example.mycapstone.reward.db.WasteItem

class WasteRepository {
    private val wasteHistory = mutableListOf<WasteItem>()
    private val rewards = listOf(
        Reward("1", "Eco Bag", 100, true),
        Reward("2", "Reusable Bottle", 200, true),
        Reward("3", "Plastic Toy", 50, false),
        Reward("4", "Glass Cup", 150, false)
    )

    fun addWasteItem(wasteItem: WasteItem){
        wasteHistory.add(wasteItem)
    }

    fun getWasteHistory(): List<WasteItem>{
        return wasteHistory
    }

    fun getRewards(): List<Reward>{
        return rewards
    }

    fun getEcoFriendly(): List<Reward>{
        return rewards.filter { it.isEcoFriendly }
    }

}
