package com.example.twitterdemo


import android.util.Log
import com.example.twitterdemo.home.foryou.ForYouTweetRepository
import org.mockito.Mockito.*
import com.example.twitterdemo.home.foryou.ForYouViewModel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.example.twitterdemo.data.TweetModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Rule
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ForYouViewModelTest {


    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private  var mockRepository = mock(ForYouTweetRepository::class.java)


    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()
    @OptIn(ExperimentalCoroutinesApi::class)
    private val testScope = TestScope(testDispatcher)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getTweetData_returns_correct_data_from_repository() {
        testScope.runTest {
            val  viewModel = ForYouViewModel(mockRepository)
            val pagingData = viewModel.getTweetData()?.toList()
            Log.d("Test", "getTweetData_returns_correct_data_from_repository: paginDataSize"+pagingData?.size )
            assert(pagingData?.size == 4)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
       // testScope.cancel()
    }
}