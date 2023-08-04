package com.example.twitterdemo.home.foryou.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.twitterdemo.api.TwitterService
import com.example.twitterdemo.data.TweetModel
import javax.inject.Inject

const val INITIAL_PAGE_URL = "1c8135b7-cd6c-4411-afab-aa2a0f481b91"
class FeedDataSource @Inject constructor(private val twitterService: TwitterService)
    : PagingSource<String, TweetModel>() {

    override fun getRefreshKey(state: PagingState<String, TweetModel>): String = INITIAL_PAGE_URL

    override suspend fun load(params: LoadParams<String>): LoadResult<String, TweetModel> {
        val pageNumber = params.key ?: INITIAL_PAGE_URL
        return try {
            val pagedResponse = twitterService.getForYouDetailTweets(pageNumber)
            val data = pagedResponse.posts
            val nextPageId = pagedResponse.pageInfo.next

            LoadResult.Page(
                data = data.orEmpty(),
                prevKey = null,
                nextKey = nextPageId
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}