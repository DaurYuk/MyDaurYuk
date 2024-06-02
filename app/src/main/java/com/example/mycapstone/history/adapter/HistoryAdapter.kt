package com.example.mycapstone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mycapstone.databinding.ItemHistoryBinding
import com.example.mycapstone.history.db.History

class HistoryAdapter(private val onDeleteClick: (History) -> Unit) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private var historyList = listOf<History>()

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
        holder.bind(historyList[position])
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    fun submitList(list: List<History>) {
        historyList = list
        notifyDataSetChanged()
    }
}
