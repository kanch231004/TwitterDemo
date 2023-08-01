package com.example.twitterdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.twitterdemo.databinding.ActivityMainBinding
import com.example.twitterdemo.home.HomeFragment
import com.example.twitterdemo.home.configs.replaceFragment
import com.example.twitterdemo.notifications.NotificationsFragment
import com.example.twitterdemo.search.SearchFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpBottomNavigationView()
        supportFragmentManager.beginTransaction().replace(R.id.fragmentBottomNav, HomeFragment()).commit()
    }

    private fun setUpBottomNavigationView() {
        binding.bottomNavMenu.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.homePage -> HomeFragment()
                R.id.search -> SearchFragment()
                R.id.notifications -> NotificationsFragment()
                R.id.message -> SearchFragment()
                else -> null
            }

            fragment?.let {
                replaceFragment(supportFragmentManager, R.id.fragmentBottomNav, it)
                return@setOnItemSelectedListener true
            } ?: return@setOnItemSelectedListener false
        }
    }
}