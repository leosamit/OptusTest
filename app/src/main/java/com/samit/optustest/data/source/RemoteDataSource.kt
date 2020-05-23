package com.samit.optustest.data.source

import com.samit.optustest.api.BaseDataSource
import com.samit.optustest.api.OptusService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val service: OptusService) :
    BaseDataSource() {
    suspend fun fetchUserInfo() =
        getResult {
            service.getUsers()
        }

    suspend fun fetchAlbum() =
        getResult {
            service.getAlbum()
        }
}