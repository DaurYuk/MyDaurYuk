package com.example.mycapstone.history.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mycapstone.R
import com.example.mycapstone.history.db.HistoryItem


class HistoryAdapter(private val historyList: List<HistoryItem>):
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>(){
    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val classification : TextView = itemView.findViewById(R.id.classificationTextView)
        val desc : TextView = itemView.findViewById(R.id.descriptionTextView)
        val image : ImageView = itemView.findViewById(R.id.imageClassification)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun getItemCount() = historyList.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val currentItem = historyList[position]
        holder.classification.text = currentItem.classification
        holder.desc.text = currentItem.descriptionHistory
        Glide.with(holder.itemView.context)
            .load(getImageResource(currentItem.image))
            .into(holder.image)
    }

    private fun getImageResource(imageName: String): Int {
        return when (imageName) {
            "img1" -> R.drawable.ic_place_holder
            "img2" -> R.drawable.ic_place_holder
            "img3" -> R.drawable.ic_place_holder
            else -> R.drawable.ic_place_holder
        }
    }
}
