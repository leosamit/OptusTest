package com.samit.optustest.ui


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.samit.optustest.data.Result
import com.samit.optustest.data.repo.UserInfoRepository
import com.samit.optustest.ui.userinfo.UserInfoUI
import com.samit.optustest.ui.userinfo.UserInfoViewModel
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Spy


@RunWith(JUnit4::class)
class UserInfoViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()
    private val repository = Mockito.mock(UserInfoRepository::class.java)
    private var viewModel = UserInfoViewModel(repository)

    @Spy
    private val userList: LiveData<Result<List<UserInfoUI>>> = MutableLiveData()

    @Before
    fun init() {
        repository.getUserInfo()
        repository.userInfoList = userList
    }

    @Test
    fun testNull() {
        MatcherAssert.assertThat(viewModel.userInfoList, CoreMatchers.notNullValue())
        Mockito.verify(repository, Mockito.never()).getUserInfo()
    }

    @Test
    fun getUserInfo() {
        viewModel.refreshUserInfo()
        Mockito.verify(repository, Mockito.never()).getUserInfo()
    }
}