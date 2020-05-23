package com.samit.optustest.api

import com.google.gson.annotations.SerializedName
import com.samit.optustest.api.ApiKeys.Companion.ALBUM_ID
import com.samit.optustest.api.ApiKeys.Companion.EMAIL
import com.samit.optustest.api.ApiKeys.Companion.ID
import com.samit.optustest.api.ApiKeys.Companion.NAME
import com.samit.optustest.api.ApiKeys.Companion.PHONE
import com.samit.optustest.api.ApiKeys.Companion.THUMBNAILURL
import com.samit.optustest.api.ApiKeys.Companion.TITLE
import com.samit.optustest.api.ApiKeys.Companion.URL

data class UserInfoApi(
    @SerializedName(ID)
    val id: Int? = null,
    @SerializedName(NAME)
    val name: String? = null,
    @SerializedName(EMAIL)
    val email: String? = null,
    @SerializedName(PHONE)
    val phone: String? = null
)

data class AlbumApi(
    @SerializedName(ID)
    val id: Int? = null,
    @SerializedName(ALBUM_ID)
    val albumID: Int? = null,
    @SerializedName(TITLE)
    val title: String? = null,
    @SerializedName(URL)
    val url: String? = null,
    @SerializedName(THUMBNAILURL)
    val thumbnailUrl: String? = null
)

interface ApiKeys {
    companion object {
        const val ID = "id"
        const val NAME = "name"
        const val EMAIL = "email"
        const val PHONE = "phone"
        const val API_GET_USERS = "users"
        const val API_GET_ALBUM = "photos"
        const val ALBUM_ID = "albumId"
        const val TITLE = "title"
        const val URL = "url"
        const val THUMBNAILURL = "thumbnailUrl"
        const val ENDPOINT = "https://jsonplaceholder.typicode.com/"
    }
}