package com.example.mycapstone

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycapstone.databinding.FragmentNewsBinding
import com.example.mycapstone.news.ui.detail.NewsDetailActivity
import com.example.mycapstone.news.ui.list.NewsAdapter
import com.example.mycapstone.news.ui.list.NewsViewModel
import com.example.mycapstone.news.data.Result
class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory: ViewModelFactory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory).get(NewsViewModel::class.java)

        newsAdapter = NewsAdapter { news ->
            val intent = Intent(activity, NewsDetailActivity::class.java)
            intent.putExtra(NewsDetailActivity.NEWS_DATA, news)
            startActivity(intent)
        }

        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = newsAdapter
        }

        // Observe the news data
        viewModel.newsList.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    newsAdapter.submitList(result.data)
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.viewError.root.visibility = View.VISIBLE
                }
            }
        }

        // Observe error state
        viewModel.errorState.observe(viewLifecycleOwner) { isError ->
            if (isError) {
                binding.viewError.root.visibility = View.VISIBLE
                binding.rvNews.visibility = View.GONE
            } else {
                binding.viewError.root.visibility = View.GONE
                binding.rvNews.visibility = View.VISIBLE
            }
        }

        // Retry button logic
        binding.viewError.retryButton.setOnClickListener {
            viewModel.fetchNews()
        }

        // Fetch news initially
        viewModel.fetchNews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}