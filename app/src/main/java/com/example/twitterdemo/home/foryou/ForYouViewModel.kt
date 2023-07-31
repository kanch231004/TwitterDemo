package com.example.twitterdemo.home.foryou

import android.provider.Contacts.Intents.UI
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.twitterdemo.data.TweetModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ForYouViewModel @Inject constructor(private val forYouTweetRepository: ForYouTweetRepository) : ViewModel() {
    val tweetResultLiveData: MutableLiveData<UIState> by lazy { MutableLiveData<UIState>() }
    private var tweetList = ArrayList<TweetModel>()
    fun getForYouTweetDetails() {
        if (tweetList.isEmpty()) {
            viewModelScope.launch {
                try {
                    tweetResultLiveData.postValue(UIState.Loading)
                    forYouTweetRepository.getForYouTweetDetails()
                        .catch {
                            tweetResultLiveData.postValue(UIState.Error(it.message))
                        }.collect {
                            tweetResponse ->
                            tweetList = tweetResponse.posts
                            tweetResultLiveData.postValue(UIState.TweetsLoaded(tweetList))
                        }
                } catch (e: Exception) {
                    tweetResultLiveData.postValue(UIState.Error(e.message))
                }
            }
        } else {
            tweetResultLiveData.postValue(UIState.TweetsLoaded(tweetList))
        }
    }
}

sealed class UIState {
    object Loading : UIState()
    data class  TweetsLoaded(val data : ArrayList<TweetModel>) : UIState()
    data class Error(var message: String?) : UIState()
}