package com.example.mycapstone.news.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.IntentCompat
import com.example.mycapstone.news.data.local.entity.NewsEntity
import com.example.mycapstone.ViewModelFactory
import com.example.mycapstone.databinding.ActivityNewsDetailBinding

class NewsDetailActivity : AppCompatActivity() {

    private lateinit var newsDetail: NewsEntity
    private lateinit var binding: ActivityNewsDetailBinding
    private val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
    private val viewModel: NewsDetailViewModel by viewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(IntentCompat.getParcelableExtra(intent, NEWS_DATA, NewsEntity::class.java)){
            if (this != null){
                newsDetail = this
                supportActionBar?.title = newsDetail.title
                binding.webView.webViewClient = WebViewClient()
                binding.webView.loadUrl(newsDetail.url.toString())

                viewModel.setNewsData(newsDetail)
            } else {
                Toast.makeText(this@NewsDetailActivity, "URL berita tidak tersedia", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
    companion object {
        const val NEWS_DATA = "data"
    }
}