package com.samit.optustest.di.builder

import androidx.lifecycle.ViewModel
import com.samit.optustest.di.qualifier.ViewModelKey
import com.samit.optustest.ui.album.AlbumViewModel
import com.samit.optustest.ui.userinfo.UserInfoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AppViewModelBuilder {
    @Binds
    @IntoMap
    @ViewModelKey(UserInfoViewModel::class)
    abstract fun bindUserInfoViewModel(userInfoViewModel: UserInfoViewModel): ViewModel

    @Binds

    @IntoMap
    @ViewModelKey(AlbumViewModel::class)
    abstract fun bindAlbumViewModel(albumViewModel: AlbumViewModel): ViewModel
}