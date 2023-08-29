package com.example.twitterdemo.home.foryou

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.twitterdemo.data.TweetModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForYouViewModel @Inject constructor(private val forYouTweetRepository: ForYouTweetRepository) : ViewModel() {
     private var tweetPagingData = flow<PagingData<TweetModel>> {
         emptyFlow<PagingData<TweetModel>>()
     }
     val tweetFlow: Flow<PagingData<TweetModel>>
         get() = tweetPagingData

    init {
        viewModelScope.launch {
            tweetPagingData =  forYouTweetRepository.getForYouTweetDetails()
        }
    }
}