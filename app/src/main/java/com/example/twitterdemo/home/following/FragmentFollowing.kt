package com.example.twitterdemo.home.following

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.twitterdemo.databinding.FragmentFollowingBinding
import com.example.twitterdemo.home.foryou.ForYouViewModel
import kotlinx.coroutines.launch

class FragmentFollowing : Fragment() {

    private var _binding: FragmentFollowingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var followingVM: FollowingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        followingVM = ViewModelProvider(requireActivity())[FollowingViewModel::class.java]
        lifecycleScope.launch {
            val model = followingVM.fetchCelebrityData()
            Log.d("Test", "onViewCreated: model "+model.toString())
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}