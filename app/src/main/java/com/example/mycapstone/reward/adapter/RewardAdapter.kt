package com.example.mycapstone.reward.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mycapstone.databinding.ItemRewardBinding
import com.example.mycapstone.reward.db.Reward

class RewardAdapter : ListAdapter<Reward, RewardAdapter.RewardViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Reward>() {
            override fun areItemsTheSame(oldItem: Reward, newItem: Reward): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Reward, newItem: Reward): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RewardViewHolder {
        val binding = ItemRewardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RewardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RewardViewHolder, position: Int) {
        val reward = getItem(position)
        holder.bind(reward)
    }

    class RewardViewHolder(private val binding: ItemRewardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(reward: Reward) {
            binding.nameTextView.text = reward.name
            binding.pointsTextView.text = "Points Required: ${reward.pointRequired}"
        }
    }
}