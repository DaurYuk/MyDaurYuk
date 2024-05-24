package com.example.mycapstone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycapstone.databinding.ActivityNewsBinding
import com.example.mycapstone.ui.list.NewsAdapter
import com.example.mycapstone.ui.list.NewsViewModel

class NewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsBinding
    private lateinit var viewModel: NewsViewModel
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        // Setup RecyclerView
        adapter = NewsAdapter()
        binding.recyclerViewNews.adapter = adapter
        binding.recyclerViewNews.layoutManager = LinearLayoutManager(this)

        // Observe data
        viewModel.newsList.observe(this, { news ->
            adapter.submitList(news)
            binding.progressBar.visibility = View.GONE
        })

        // Observe error state
        viewModel.errorState.observe(this, { isError ->
            if (isError) {
                binding.viewError.visibility = View.VISIBLE
                binding.recyclerViewNews.visibility = View.GONE
            } else {
                binding.viewError.visibility = View.GONE
                binding.recyclerViewNews.visibility = View.VISIBLE
            }
        })

        // Retry button logic
        binding.viewError.findViewById<Button>(R.id.retry_button).setOnClickListener {
            viewModel.retryFetchNews()
        }

        // Fetch news initially
        viewModel.fetchNews()
    }
}