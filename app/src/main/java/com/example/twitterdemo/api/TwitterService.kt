package com.example.twitterdemo.api

import com.example.twitterdemo.data.TweetResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object RetrofitFactory {
    private val okHttpClient =  OkHttpClient.Builder().addNetworkInterceptor(
        HttpLoggingInterceptor(
            HttpLoggingInterceptor.Logger {
            }).setLevel(HttpLoggingInterceptor.Level.BASIC))
        .build()

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://run.mocky.io")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getService() = retrofit.create(TwitterService::class.java)
}

interface TwitterService {
    @GET("/v3/fa23366d-eab0-445f-b2e4-fe857c1d21f0")
    suspend fun getForYouDetailTweets(): TweetResponse
}