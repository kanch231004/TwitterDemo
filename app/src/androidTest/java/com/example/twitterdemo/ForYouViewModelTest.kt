package com.example.twitterdemo


import com.example.twitterdemo.home.foryou.ForYouTweetRepository
import org.mockito.Mockito.*
import com.example.twitterdemo.home.foryou.ForYouViewModel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.example.twitterdemo.data.TweetModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Rule
import org.junit.Before
import org.junit.Test

class ForYouViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private  var mockRepository = mock(ForYouTweetRepository::class.java)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()
    @OptIn(ExperimentalCoroutinesApi::class)
    private val testScope = TestScope(testDispatcher)
    val  viewModel = ForYouViewModel(mockRepository)
    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun verify_repository_getForYouTweetDetails_is_called_when_viewModel_is_created() {
        testScope.runTest {
            verify(mockRepository,times(1)).getForYouTweetDetails()
        }
    }
}