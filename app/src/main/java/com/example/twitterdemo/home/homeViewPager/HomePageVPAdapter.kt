package com.example.twitterdemo.home.homeViewPager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.twitterdemo.home.configs.FOLLOWING_FRAGMENT_POSITION
import com.example.twitterdemo.home.configs.FOR_YOU_POSITION
import com.example.twitterdemo.home.configs.TOTAL_FRAGMENT_COUNT
import com.example.twitterdemo.home.following.FragmentFollowing
import com.example.twitterdemo.home.foryou.ForYouFragment

class HomePageVPAdapter(private var fragmentActivity: FragmentActivity?) : FragmentStateAdapter(fragmentActivity!!) {

    override fun getItemCount(): Int {
        return TOTAL_FRAGMENT_COUNT
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            FOR_YOU_POSITION -> {
                ForYouFragment()
            }
            FOLLOWING_FRAGMENT_POSITION -> {
                FragmentFollowing()
            }
            else -> {
                ForYouFragment()
            }
        }
    }
}