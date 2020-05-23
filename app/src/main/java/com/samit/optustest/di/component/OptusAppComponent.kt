package com.samit.optustest.di.component

import android.app.Application
import com.samit.optustest.OptusApp
import com.samit.optustest.di.builder.ActivityBuilder
import com.samit.optustest.di.module.AppModule
import com.samit.optustest.di.module.ContextModule
import com.samit.optustest.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        ContextModule::class,
        ActivityBuilder::class]
)
interface OptusAppComponent : AndroidInjector<OptusApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): OptusAppComponent
    }
}