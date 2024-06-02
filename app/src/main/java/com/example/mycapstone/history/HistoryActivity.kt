package com.example.mycapstone.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycapstone.HistoryAdapter
import com.example.mycapstone.databinding.ActivityHistoryBinding
import com.example.mycapstone.history.db.History
import com.example.mycapstone.history.db.HistoryDb
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewHistory.layoutManager = LinearLayoutManager(this)
        val adapter = HistoryAdapter { history ->
            deleteHistory(history)
        }
        binding.recyclerViewHistory.adapter = adapter

        val db = HistoryDb.getDatabase(applicationContext)
        lifecycleScope.launch{
            val historyList = db.historyDao().getAllHistory()
            adapter.submitList(historyList)
        }
    }

    private fun deleteHistory(history: History) {
        val db = HistoryDb.getDatabase(applicationContext)
        lifecycleScope.launch {
            db.historyDao().deleteHistory(history)
            val updatedHistoryList = db.historyDao().getAllHistory()
            (binding.recyclerViewHistory.adapter as HistoryAdapter).submitList(updatedHistoryList)
        }
    }

}