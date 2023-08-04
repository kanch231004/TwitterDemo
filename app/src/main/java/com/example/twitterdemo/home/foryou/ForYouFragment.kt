package com.example.twitterdemo.home.foryou

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.twitterdemo.databinding.FragmentForYouBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ForYouFragment : Fragment() {

    private var _binding: FragmentForYouBinding? = null
    private val binding get() = _binding!!
    private val forYouViewModel: ForYouViewModel by viewModels()
    private lateinit var tweetAdapter: ForYouListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForYouBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tweetAdapter = ForYouListAdapter()
        binding.apply {
            rvTweet.adapter = tweetAdapter
        }

        subscribeUI()
    }

    private fun subscribeUI() {
        val pageData = forYouViewModel.getTweetData()
        lifecycleScope.launch {
            pageData?.liveData?.collectLatest {
                tweetAdapter.submitData(it)
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
