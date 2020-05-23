package com.samit.optustest.data.mapper

import com.samit.optustest.api.AlbumApi
import com.samit.optustest.api.UserInfoApi
import com.samit.optustest.ui.album.AlbumUI
import com.samit.optustest.ui.userinfo.UserInfoUI

fun toUserUI(userApi: UserInfoApi): UserInfoUI =
    UserInfoUI(
        id = userApi.id,
        name = userApi.name,
        phone = userApi.phone,
        email = userApi.email
    )

fun toAlbumUI(albumApi: AlbumApi): AlbumUI =
    AlbumUI(
        id = albumApi.id,
        albumID = albumApi.albumID,
        title = albumApi.title,
        url = albumApi.url,
        thumbnailUrl = albumApi.thumbnailUrl
    )