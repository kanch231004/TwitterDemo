package com.example.twitterdemo.home.foryou

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.twitterdemo.databinding.FragmentForYouBinding
import dagger.hilt.android.AndroidEntryPoint

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
        forYouViewModel.getForYouTweetDetails()
    }

    private fun subscribeUI() {
        forYouViewModel.tweetResultLiveData.observe(viewLifecycleOwner) {uiState ->
            when (uiState) {
                is UIState.Loading -> {
                    //show progress bar
                }
                is UIState.TweetsLoaded -> {
                    tweetAdapter.submitList(uiState.data)
                }
                is UIState.Error -> {
                    Toast.makeText(context, "error ${uiState.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}