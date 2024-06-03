package com.example.mycapstone.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycapstone.databinding.ActivityHistoryBinding
import com.example.mycapstone.history.adapter.HistoryAdapter
import com.example.mycapstone.history.db.History
import com.example.mycapstone.history.db.HistoryDb
import com.example.mycapstone.history.db.HistoryViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var viewModel: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Dapatkan instance dari DAO
        val dao = HistoryDb.getDatabase(applicationContext).historyDao()

        // Buat ViewModelFactory dengan DAO
        val viewModelFactory = HistoryViewModelFactory(dao)

        // Inisialisasi ViewModel menggunakan ViewModelFactory
        viewModel = ViewModelProvider(this, viewModelFactory).get(HistoryViewModel::class.java)

        val adapter = HistoryAdapter { history ->
            deleteHistory(history)
        }
        binding.recyclerViewHistory.adapter = adapter
        binding.recyclerViewHistory.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launchWhenStarted {
            viewModel.historyFlow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    private fun deleteHistory(history: History) {
        val db = HistoryDb.getDatabase(applicationContext)
        lifecycleScope.launch {
            db.historyDao().deleteHistory(history)
            (binding.recyclerViewHistory.adapter as HistoryAdapter).refresh()
        }
    }
}

