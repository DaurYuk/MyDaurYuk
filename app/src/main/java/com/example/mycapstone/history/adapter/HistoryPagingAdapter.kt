package com.example.mycapstone.history.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mycapstone.databinding.ItemHistoryBinding
import com.example.mycapstone.history.db.History
class HistoryPagingAdapter(private val onDeleteClick: (History) -> Unit) : PagingDataAdapter<History, HistoryPagingAdapter.HistoryViewHolder>(DIFF_CALLBACK) {

    class HistoryViewHolder(private val binding: ItemHistoryBinding, private val onDeleteClick: (History) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(history: History) {
            binding.imageClassification.setImageURI(history.imagePath.toUri())
            binding.classificationTextView.text = history.result
            binding.confidenceTextView.text = "Confidence: ${history.confidenceScore}%"

            binding.closeImageView.setOnClickListener {
                onDeleteClick(history)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding, onDeleteClick)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val history = getItem(position)
        if (history != null) {
            holder.bind(history)
        }
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
