package com.example.twitterdemo.home.following

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.twitterdemo.databinding.FragmentFollowingBinding

class FragmentFollowing : Fragment() {

    private var _binding: FragmentFollowingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}