package com.example.twitterdemo.di

import com.example.twitterdemo.api.RetrofitFactory
import com.example.twitterdemo.api.TwitterService
import com.example.twitterdemo.home.foryou.ForYouTweetRepoImpl
import com.example.twitterdemo.home.foryou.ForYouTweetRepository
import com.example.twitterdemo.home.foryou.paging.FeedDataItemSource
import com.example.twitterdemo.home.foryou.paging.FeedDataPagingSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Provides
    @Singleton
    fun providesAPIService(): TwitterService = RetrofitFactory.getService()

    @Provides
    @Singleton
    fun providesFeedSource(feedDataItemSource: FeedDataItemSource): FeedDataPagingSource = feedDataItemSource

    @Provides
    @Singleton
    fun providesForYouRepository(feedDataSource: FeedDataPagingSource): ForYouTweetRepository = ForYouTweetRepoImpl(
       feedDataSource
    )
}
