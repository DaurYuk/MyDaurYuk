package com.example.mycapstone.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mycapstone.R
import com.example.mycapstone.databinding.ActivityHistoryBinding
import com.example.mycapstone.history.adapter.HistoryAdapter
import com.example.mycapstone.history.db.HistoryItem

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewHistory)

        val historyList = dummyHistoryList()
        recyclerView.adapter = HistoryAdapter(historyList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }
    private fun dummyHistoryList(): List<HistoryItem> {
        val list = ArrayList<HistoryItem>()
        list.add(HistoryItem("Item 1", "deskripsi 1", "img1"))
        list.add(HistoryItem("Item 2", "deskripsi 2", "img1"))
        list.add(HistoryItem("Item 3", "deskripsi 3", "img1"))
        list.add(HistoryItem("Item 4", "deskripsi 4", "img1"))
        return list
    }
}