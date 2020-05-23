package com.samit.optustest.di.builder

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        (AppViewModelBuilder::class)
    ]
)
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}