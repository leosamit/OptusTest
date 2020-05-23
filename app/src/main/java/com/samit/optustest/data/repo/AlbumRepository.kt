package com.samit.optustest.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.samit.optustest.data.Result
import com.samit.optustest.data.mapper.toAlbumUI
import com.samit.optustest.data.source.RemoteDataSource
import com.samit.optustest.ui.album.AlbumUI
import com.samit.optustest.util.CoroutineDispatcherProvider
import com.samit.optustest.util.postError
import com.samit.optustest.util.postLoading
import com.samit.optustest.util.postSuccess
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlbumRepository @Inject constructor(
    private val remoteSource: RemoteDataSource,
    dispatcherProvider: CoroutineDispatcherProvider
) {
    private val _albumLivedata: MutableLiveData<Result<List<AlbumUI>>> =
        MutableLiveData()
    val albumList: LiveData<Result<List<AlbumUI>>> = _albumLivedata

    private val parentJob = Job()
    private val scope = CoroutineScope(dispatcherProvider.mainDispatcher + parentJob)

    fun getAlbum() {
        _albumLivedata.postLoading()
        scope.launch {
            val responseStatus = remoteSource.fetchAlbum()
            when (responseStatus.status) {
                Result.Status.SUCCESS -> {
                    _albumLivedata.postSuccess(responseStatus.data!!.map { toAlbumUI(it) })
                }
                Result.Status.ERROR -> {
                    _albumLivedata.postError(responseStatus.message.toString())
                }
            }
        }
    }

    //Cancel Coroutine scope
    fun cancelAllRequests() {
        parentJob.cancelChildren()
    }
}