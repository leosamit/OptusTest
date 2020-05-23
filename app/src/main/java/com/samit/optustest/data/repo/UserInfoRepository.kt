package com.samit.optustest.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.samit.optustest.data.Result
import com.samit.optustest.data.mapper.toUserUI
import com.samit.optustest.data.source.RemoteDataSource
import com.samit.optustest.ui.userinfo.UserInfoUI
import com.samit.optustest.util.CoroutineDispatcherProvider
import com.samit.optustest.util.postError
import com.samit.optustest.util.postLoading
import com.samit.optustest.util.postSuccess
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserInfoRepository @Inject constructor(
    private val remoteSource: RemoteDataSource,
    dispatcherProvider: CoroutineDispatcherProvider
) {
    private val _userInfoLivedata: MutableLiveData<Result<List<UserInfoUI>>> =
        MutableLiveData()
    var userInfoList: LiveData<Result<List<UserInfoUI>>> = _userInfoLivedata

    private val parentJob = Job()
    private val scope = CoroutineScope(dispatcherProvider.mainDispatcher + parentJob)

    fun getUserInfo() {
        _userInfoLivedata.postLoading()
        scope.launch {
            val responseStatus = remoteSource.fetchUserInfo()
            when (responseStatus.status) {
                Result.Status.SUCCESS -> {
                    Timber.d("Samit Success")
                    _userInfoLivedata.postSuccess(responseStatus.data!!.map { toUserUI(it) })
                }
                Result.Status.ERROR -> {
                    Timber.d("Samit Success")
                    _userInfoLivedata.postError(responseStatus.message.toString())
                }
            }
        }
    }

    //Cancel Coroutine scope
    fun cancelAllRequests() {
        parentJob.cancelChildren()
    }
}