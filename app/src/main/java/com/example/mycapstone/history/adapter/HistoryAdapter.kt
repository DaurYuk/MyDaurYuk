package com.example.mycapstone.history.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mycapstone.R
import com.example.mycapstone.databinding.ItemHistoryBinding
import com.example.mycapstone.history.db.History
import com.example.mycapstone.history.db.HistoryDb


class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private var historyList = listOf<History>()

    class HistoryViewHolder(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(history: History) {
            binding.imageClassification.setImageURI(history.imagePath.toUri())
            binding.classificationTextView.text = history.result
            binding.confidenceTextView.text = "Confidence: ${history.confidenceScore}%"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(historyList[position])
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    fun submitList(list: List<History>) {
        historyList = list
        notifyDataSetChanged()
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<History>() {
            override fun areItemsTheSame(oldItem: History, newItem: History): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: History, newItem: History): Boolean {
                return oldItem == newItem
            }
        }
    }
}
