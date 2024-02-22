package com.example.twitterdemo.home.following

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.twitterdemo.api.FakeAPI
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.example.twitterdemo.api.Celebrity
import com.example.twitterdemo.api.Movie
import com.example.twitterdemo.api.UIModel
import com.example.twitterdemo.home.foryou.ForYouViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import retrofit2.Response
import kotlin.system.measureTimeMillis
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime


class FollowingViewModel (val fakeAPI: FakeAPI = FakeAPI()): ViewModel() {


    @OptIn(ExperimentalTime::class)
    suspend fun fetchCelebrityData(): UIModel {
        var res: UIModel;
        val time = measureTimeMillis {

            val celebrity: Deferred<Response<Celebrity>> = viewModelScope.async(Dispatchers.Main) {
                fakeAPI.getUserName()
            }
            val celeb = celebrity.await().body()

            val bestMovie = viewModelScope.async(Dispatchers.Main) { fakeAPI.getBestMovie(celeb?.userId!!) }
            val followers = viewModelScope.async { fakeAPI.getFollowers(celeb?.userId!!) }
            val besMovie: Movie = bestMovie.await().body()!!
            val followersList = followers.await().body()

            viewModelScope.launch {
                val response = fakeAPI.postUpdate(followersList?.toList()!!, besMovie)
            }
            res =  UIModel(name = celeb?.name!!, movie = besMovie, listFollower = followersList!!)
        }
        Log.d("test", "fetchCelebrityData: $time")
        return res
    }
}