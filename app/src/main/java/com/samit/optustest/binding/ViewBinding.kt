package com.samit.optustest.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.samit.optustest.R
import com.samit.optustest.ui.album.AlbumAdapter
import com.samit.optustest.ui.album.AlbumUI
import com.samit.optustest.ui.userinfo.UserInfoAdapter
import com.samit.optustest.ui.userinfo.UserInfoUI
import com.squareup.picasso.Picasso

@BindingAdapter(value = ["app:userInfoList"])
fun setUserInfoList(rv: RecyclerView, items: List<UserInfoUI>?) {
    if (rv.adapter as? UserInfoAdapter == null) {
        rv.adapter = UserInfoAdapter()
    }
    (rv.adapter as UserInfoAdapter).submitList(items)
}

@BindingAdapter(value = ["app:albums"])
fun setAlbumList(rv: RecyclerView, items: List<AlbumUI>?) {
    if (rv.adapter as? AlbumAdapter == null) {
        rv.adapter = AlbumAdapter()
    }
    (rv.adapter as AlbumAdapter).submitList(items)
}

@BindingAdapter("albumImage")
fun setAlbumImage(view: ImageView, src: String?) {
    Picasso.get()
        .load(src)
        .error(R.drawable.placeholder)
        .placeholder(R.drawable.placeholder)
        .into(view)
}