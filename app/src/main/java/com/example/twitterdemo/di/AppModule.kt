package com.example.twitterdemo.di

import com.example.twitterdemo.api.RetrofitFactory
import com.example.twitterdemo.api.TwitterService
import com.example.twitterdemo.home.foryou.ForYouTweetRepoImpl
import com.example.twitterdemo.home.foryou.ForYouTweetRepository
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
    fun providesForYouRepository(apiService: TwitterService): ForYouTweetRepository = ForYouTweetRepoImpl(apiService)
}