package com.example.mycapstone.ui.list

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mycapstone.R
import com.example.mycapstone.data.local.entity.NewsEntity
import com.example.mycapstone.databinding.ItemNewsBinding
import com.example.mycapstone.utils.DateFormatter
import java.util.TimeZone

class NewsAdapter(private val onItemClick: (NewsEntity) -> Unit) : ListAdapter<NewsEntity, NewsAdapter.MyViewHolder>(
    DIFF_CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, onItemClick)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val news = getItem(position)
        holder.bind(news)
    }

    class MyViewHolder(private val binding: ItemNewsBinding, val onItemClick: (NewsEntity) -> Unit) : RecyclerView.ViewHolder(
        binding.root
    ) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(news: NewsEntity) {
            binding.newsTitle.text = news.title
            binding.newsDate.text = DateFormatter.formatDate(news.publishedAt, TimeZone.getDefault().id)

            // Log the URL for debugging purposes
            Log.d("NewsAdapter", "Loading image from URL: ${news.urlToImage}")

            if (news.urlToImage.isNullOrEmpty()) {
                binding.newsImage.setImageResource(R.drawable.ic_error)
            } else {
                Glide.with(itemView.context)
                    .load(news.urlToImage)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(binding.newsImage)
            }

            itemView.setOnClickListener {
                onItemClick(news)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<NewsEntity> =
            object : DiffUtil.ItemCallback<NewsEntity>() {
                override fun areItemsTheSame(oldUser: NewsEntity, newUser: NewsEntity): Boolean {
                    return oldUser.title == newUser.title
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldUser: NewsEntity, newUser: NewsEntity): Boolean {
                    return oldUser == newUser
                }
            }
    }
}