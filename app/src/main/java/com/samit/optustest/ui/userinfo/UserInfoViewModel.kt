package com.samit.optustest.ui.userinfo

import androidx.lifecycle.ViewModel
import com.samit.optustest.data.repo.UserInfoRepository
import javax.inject.Inject

class UserInfoViewModel @Inject constructor(private val repository: UserInfoRepository) :
    ViewModel() {

    val userInfoList = repository.userInfoList

    init {
        repository.getUserInfo()
    }

    override fun onCleared() {
        super.onCleared()
        repository.cancelAllRequests()
    }
    fun refreshUserInfo(){
        repository.getUserInfo()
    }
}