package com.example.mycapstone.reward.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mycapstone.databinding.ItemWasteBinding
import com.example.mycapstone.reward.db.WasteItem

class WasteHistoryAdapter : ListAdapter<WasteItem, WasteHistoryAdapter.WasteViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WasteItem>() {
            override fun areItemsTheSame(oldItem: WasteItem, newItem: WasteItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: WasteItem, newItem: WasteItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WasteViewHolder {
        val binding = ItemWasteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WasteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WasteViewHolder, position: Int) {
        val wasteItem = getItem(position)
        holder.bind(wasteItem)
    }

    class WasteViewHolder(private val binding: ItemWasteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(wasteItem: WasteItem) {
            binding.typeTextView.text = wasteItem.type
            binding.dateTextView.text = wasteItem.date
        }
    }
}
