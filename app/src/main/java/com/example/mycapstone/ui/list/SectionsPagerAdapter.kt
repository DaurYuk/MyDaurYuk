package com.example.mycapstone.ui.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mycapstone.NewsFragment

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun createFragment(position: Int): Fragment {
        val fragment = NewsFragment()
        val bundle = Bundle()
        bundle.putString(NewsFragment.ARG_TAB, NewsFragment.TAB_NEWS)
        fragment.arguments = bundle
        return fragment
    }

    override fun getItemCount(): Int {
        return 1
    }
}
