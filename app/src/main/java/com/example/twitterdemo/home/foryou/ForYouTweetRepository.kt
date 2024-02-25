package com.example.twitterdemo.home.foryou

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.twitterdemo.api.TwitterService
import com.example.twitterdemo.data.TweetModel
import com.example.twitterdemo.home.foryou.paging.FeedDataPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// Should be in pageConfigUtils
const val PAGE_SIZE = 20
const val PREFETCH_DISTANCE = 3

class ForYouTweetRepoImpl @Inject constructor(private val feedDataSource: FeedDataPagingSource): ForYouTweetRepository {
    override suspend fun getForYouTweetDetails(): Flow<PagingData<TweetModel>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = PREFETCH_DISTANCE),
        pagingSourceFactory = { feedDataSource }
    ).flow

}

interface ForYouTweetRepository {
    suspend fun getForYouTweetDetails():Flow<PagingData<TweetModel>>
}