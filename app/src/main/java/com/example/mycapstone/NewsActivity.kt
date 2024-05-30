package com.example.mycapstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycapstone.databinding.ActivityNewsBinding
import com.example.mycapstone.news.ui.list.NewsViewModel
import com.example.mycapstone.news.ui.detail.NewsDetailActivity
import com.example.mycapstone.news.ui.list.NewsAdapter
import com.example.mycapstone.news.data.Result
class NewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsBinding
    private lateinit var viewModel: NewsViewModel
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]

        // Setup RecyclerView
        adapter = NewsAdapter { news ->
            // Tindakan yang akan dijalankan ketika item berita diklik
            val intent = Intent(this@NewsActivity, NewsDetailActivity::class.java)
            intent.putExtra(NewsDetailActivity.NEWS_DATA, news)
            startActivity(intent)
        }
        binding.recyclerViewNews.adapter = adapter
        binding.recyclerViewNews.layoutManager = LinearLayoutManager(this)

        // Observe data
        viewModel.newsList.observe(this) { result ->
            when (result) {
                is Result.Success -> {
                    adapter.submitList(result.data)
                    binding.progressBar.visibility = View.GONE
                }

                is Result.Error -> {
                    binding.viewError.root.visibility = View.VISIBLE
                    binding.recyclerViewNews.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    // Menampilkan pesan kesalahan
                    val errorMessage = "Failed to load news: ${result.message}"
                    Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                }

                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }

        // Fetch news initially
        viewModel.fetchNews()
    }
}
