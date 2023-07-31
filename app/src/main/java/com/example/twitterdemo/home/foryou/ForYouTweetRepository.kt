package com.example.twitterdemo.home.foryou

import com.example.twitterdemo.api.TwitterService
import com.example.twitterdemo.data.TweetResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ForYouTweetRepoImpl @Inject constructor(private val twitterService: TwitterService): ForYouTweetRepository {
    override suspend fun getForYouTweetDetails(): Flow<TweetResponse> {
        return flowOf(twitterService.getForYouDetailTweets()).flowOn(Dispatchers.IO)
    }
}

interface ForYouTweetRepository {
    suspend fun getForYouTweetDetails(): Flow<TweetResponse>
}