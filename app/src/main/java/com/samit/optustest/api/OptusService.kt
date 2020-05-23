package com.samit.optustest.api

import com.samit.optustest.api.ApiKeys.Companion.API_GET_ALBUM
import com.samit.optustest.api.ApiKeys.Companion.API_GET_USERS
import retrofit2.Response
import retrofit2.http.GET

interface OptusService {
    @GET(API_GET_USERS)
    suspend fun getUsers(): Response<List<UserInfoApi>>

    @GET(API_GET_ALBUM)
    suspend fun getAlbum(): Response<List<AlbumApi>>
}