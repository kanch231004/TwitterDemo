package com.example.twitterdemo.api

import com.example.twitterdemo.data.TweetResponse
import kotlinx.coroutines.delay
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton

object RetrofitFactory {
    private val okHttpClient =  OkHttpClient.Builder().addNetworkInterceptor(
        HttpLoggingInterceptor(
            HttpLoggingInterceptor.Logger {
            }).setLevel(HttpLoggingInterceptor.Level.BASIC))
        .build()

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://www.jsonkeeper.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getService() = retrofit.create(TwitterService::class.java)
}

interface TwitterService {
    @GET("/b/{pageId}")
    suspend fun getForYouDetailTweets(@Path("pageId") page: String?): Response<TweetResponse>
}

@Singleton
class FakeAPI {
    suspend fun getUserName() : Response<Celebrity>{
        delay(1000)
        return Response.success(Celebrity(name = "Bradd Pitt", userId = 1001))
    }

    suspend fun getBestMovie(userId: Int): Response<Movie> {
        delay(1500)
        return Response.success(Movie("foreignMovie", 10))
    }

    suspend fun getFollowers(userId: Int): Response<List<Follower>> {
        delay(1500)
        val follower1 = Follower("Kanchan")
        val follower2 = Follower("Mayank")
        return Response.success(listOf(follower1, follower2))
    }

    suspend fun postUpdate(followerList: List<Follower>, bestMovie: Movie): Response<String> {
        if (followerList.size == 2 && bestMovie.equals("Bradd Pitt"))
            return Response.success("Success")
        return Response.error(400, "Failed".toResponseBody() )
    }
}

data class Celebrity(val name : String , val userId: Int )
data class Movie(val movieName : String, val movieId: Int )
data class Follower(val followerName: String )

data class UIModel(val listFollower: List<Follower>, val movie: Movie, val name: String)