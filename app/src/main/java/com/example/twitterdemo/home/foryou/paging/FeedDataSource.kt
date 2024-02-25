package com.example.twitterdemo.home.foryou.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.twitterdemo.api.TwitterService
import com.example.twitterdemo.data.TweetModel
import javax.inject.Inject

const val INITIAL_PAGE_URL = "7efe9576-a3ce-4db4-96a4-51d4e3f8bc9a"
class FeedDataItemSource @Inject constructor(private val twitterService: TwitterService)
    : FeedDataPagingSource() {

    override fun getRefreshKey(state: PagingState<String, TweetModel>): String = INITIAL_PAGE_URL

    override suspend fun load(params: LoadParams<String>): LoadResult<String, TweetModel> {
        val pageNumber = params.key ?: INITIAL_PAGE_URL
        return try {

            val pagedResponse = twitterService.getForYouDetailTweets(pageNumber)
            if (pagedResponse.isSuccessful && pagedResponse.body() != null) {
                val data = pagedResponse.body()?.posts
                val nextPageId = pagedResponse.body()?.pageInfo?.next
                LoadResult.Page(
                    data = data.orEmpty(),
                    prevKey = null,
                    nextKey = nextPageId
                )
            } else {

                LoadResult.Error(Throwable(pagedResponse.message()))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}

abstract class FeedDataPagingSource : PagingSource<String, TweetModel>()