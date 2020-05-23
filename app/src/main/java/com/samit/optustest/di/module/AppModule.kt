package com.samit.optustest.di.module

import com.samit.optustest.util.CoroutineDispatcherProvider
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers

@Module
class AppModule {
    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcherProvider {
        return CoroutineDispatcherProvider(
            Dispatchers.Main,
            Dispatchers.IO,
            Dispatchers.Default
        )
    }
}