package com.example.twitterdemo.home.foryou

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.twitterdemo.databinding.FragmentForYouBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ForYouFragment : Fragment() {

    private var _binding: FragmentForYouBinding? = null
    private val binding get() = _binding!!
    private lateinit var forYouViewModel: ForYouViewModel
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
        forYouViewModel = ViewModelProvider(requireActivity())[ForYouViewModel::class.java]
        tweetAdapter = ForYouListAdapter()
        binding.apply {
            rvTweet.adapter = tweetAdapter
        }
        listenLoadingState()
        subscribeUI()
    }

    private fun listenLoadingState() {
        tweetAdapter.addLoadStateListener { loadState ->
            binding.apply {

                when (loadState.refresh) {
                    is LoadState.Loading -> {
                        viewStubPb.visibility = View.VISIBLE
                        viewStub.visibility = View.GONE
                    }

                    is LoadState.NotLoading -> {
                        viewStubPb.visibility = View.GONE
                    }

                    is LoadState.Error -> {
                        viewStub.visibility = View.VISIBLE
                        viewStubPb.visibility = View.GONE
                        val error = loadState.refresh as? LoadState.Error
                        error.let {
                            viewStub.text = it?.error?.message
                        }
                    }

                    else -> { // Do nothing}
                    }
                }
            }
        }
    }

    private fun subscribeUI() {
        lifecycleScope.launch {
            val pageData = forYouViewModel.tweetFlow
            pageData.catch {
                binding.viewStub.visibility = View.VISIBLE
            }.
            collectLatest {
                binding.rvTweet.visibility = View.VISIBLE
                binding.viewStubPb.visibility = View.GONE
                tweetAdapter.submitData(it)
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
