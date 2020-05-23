package com.samit.optustest.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.samit.optustest.api.OptusService
import com.samit.optustest.data.repo.UserInfoRepository
import com.samit.optustest.data.source.RemoteDataSource
import com.samit.optustest.util.CoroutineDispatcherProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class UserInfoRepoTest {
    private lateinit var repository: UserInfoRepository
    private val service = Mockito.mock(OptusService::class.java)
    private val remoteDataSource = RemoteDataSource(service)
    private val dispatcherProvider = CoroutineDispatcherProvider(
        Dispatchers.Main,
        Dispatchers.IO, Dispatchers.Default
    )
    private val mockRemoteDataSource = Mockito.spy(remoteDataSource)

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        repository = UserInfoRepository(remoteDataSource, dispatcherProvider)
    }

    @Test
    fun loadUserInfo() {
        runBlocking {
            repository.getUserInfo()
            Mockito.verify(remoteDataSource, Mockito.never()).fetchUserInfo()
            Mockito.verifyZeroInteractions(remoteDataSource)
        }
    }
}