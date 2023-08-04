package com.example.twitterdemo.home.foryou

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.twitterdemo.data.TweetModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForYouViewModel @Inject constructor(private val forYouTweetRepository: ForYouTweetRepository) : ViewModel() {
    private var tweetData : PagedData? = null
    private var fetchTweetJob: Job? = null

    fun getTweetData(): PagedData? {
        if (tweetData == null) {
            fetchTweetJob =  viewModelScope.launch {
                tweetData = PagedData(
                    forYouTweetRepository.getForYouTweetDetails().cachedIn(viewModelScope)
                )
            }
        }
        return tweetData
    }

    override fun onCleared() {
        super.onCleared()
        fetchTweetJob = null
    }
}

data class PagedData(var pagingDataFlow: Flow<PagingData<TweetModel>>)