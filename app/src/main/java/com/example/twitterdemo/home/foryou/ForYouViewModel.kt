package com.example.twitterdemo.home.foryou

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.twitterdemo.data.TweetModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ForYouViewModel @Inject constructor(private val forYouTweetRepository: ForYouTweetRepository) : ViewModel() {
    private var tweetData : Flow<PagingData<TweetModel>>? = null
    suspend fun getTweetData(): Flow<PagingData<TweetModel>>? {
        Log.d("ViewModel", "getTweetData: called")
        return if (tweetData == null) {
            tweetData = forYouTweetRepository.getForYouTweetDetails()
            tweetData
        } else {
            tweetData
        }
    }
}