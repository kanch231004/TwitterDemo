package com.example.twitterdemo.home.foryou

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
        return if (tweetData == null) {
            tweetData = forYouTweetRepository.getForYouTweetDetails().cachedIn(viewModelScope)
            tweetData
        } else {
            tweetData
        }
    }
}