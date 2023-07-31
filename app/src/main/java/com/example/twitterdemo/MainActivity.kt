package com.example.twitterdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.twitterdemo.databinding.ActivityMainBinding
import com.example.twitterdemo.home.HomeFragment
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
            when (item.itemId) {
                R.id.homePage -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentBottomNav, HomeFragment()).commit()
                    return@setOnItemSelectedListener true
                }

                R.id.search -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentBottomNav, SearchFragment()).commit()
                    return@setOnItemSelectedListener true
                }

                R.id.notifications -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentBottomNav, NotificationsFragment()).commit()
                    return@setOnItemSelectedListener true
                }

                R.id. message -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentBottomNav, SearchFragment()).commit()
                    return@setOnItemSelectedListener true
                }
                else -> return@setOnItemSelectedListener true
            }
        }
    }
}