package com.example.twitterdemo.home.foryou

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.twitterdemo.data.TweetModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForYouViewModel @Inject constructor(private val forYouTweetRepository: ForYouTweetRepository) : ViewModel() {
    private var tweetData : PagedData? = null

    fun getTweetData(): PagedData? {
        if (tweetData == null) {
            viewModelScope.launch {
                tweetData = PagedData(
                    forYouTweetRepository.getForYouTweetDetails().cachedIn(viewModelScope)
                )
            }
        }
        return tweetData
    }
}

data class PagedData(var liveData: Flow<PagingData<TweetModel>>)