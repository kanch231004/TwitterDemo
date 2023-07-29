package com.example.twitterdemo.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.twitterdemo.databinding.FragmentHomeBinding
import com.example.twitterdemo.home.configs.FOLLOWING_FRAGMENT_POSITION
import com.example.twitterdemo.home.configs.FOR_YOU_POSITION
import com.example.twitterdemo.home.configs.getTabByPosition
import com.example.twitterdemo.home.homeViewPager.HomePageVPAdapter
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var hpVPAdapter: HomePageVPAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Twitter", "onViewCreated: ")
        setupHpViewPager()
        setupTabLayouts()
    }

    private fun setupTabLayouts() {
        TabLayoutMediator(binding.tabLayoutHP, binding.viewPager) {
            tab, position ->
            tab.text = getTabByPosition(position, activity?.baseContext)
        }.attach()
    }

    private fun setupHpViewPager() {
        hpVPAdapter = HomePageVPAdapter(activity)
        binding.viewPager.adapter = hpVPAdapter
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when(position) {
                    FOR_YOU_POSITION, FOLLOWING_FRAGMENT_POSITION -> binding.tabLayoutHP.getTabAt(position)?.select()
                }
                super.onPageSelected(position)
            }
        })
    }
}