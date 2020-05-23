package com.samit.optustest.ui.album

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AlbumUI(
    val id: Int?,
    val albumID: Int?,
    val url: String?,
    val title: String?,
    val thumbnailUrl: String?
) : Parcelable