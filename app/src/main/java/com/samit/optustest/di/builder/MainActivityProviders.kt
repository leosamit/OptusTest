package com.samit.optustest.di.builder

import com.samit.optustest.ui.album.AlbumFragment
import com.samit.optustest.ui.photo.PhotoFragment
import com.samit.optustest.ui.userinfo.UserInfoFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityProviders {

    @ContributesAndroidInjector
    abstract fun provideUserInfoFragment(): UserInfoFragment
    @ContributesAndroidInjector
    abstract fun provideAlbumFragment(): AlbumFragment
    @ContributesAndroidInjector
    abstract fun providePhotoFragment(): PhotoFragment
}