package com.example.mycapstone.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycapstone.databinding.ActivityHistoryBinding
import com.example.mycapstone.history.adapter.HistoryAdapter
import com.example.mycapstone.history.db.HistoryDb
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewHistory.layoutManager = LinearLayoutManager(this)
        val adapter = HistoryAdapter()
        binding.recyclerViewHistory.adapter = adapter

        val db = HistoryDb.getDatabse(applicationContext)
        lifecycleScope.launch{
            val historyList = db.historyDao().getAllHistory()
            adapter.submitList(historyList)
        }
    }

}