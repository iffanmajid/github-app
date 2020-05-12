package com.android.mobile.injection.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import com.android.mobile.injection.ApplicationContext

@Module(includes = arrayOf(ApiModule::class))
class AppModule(private val application: Application) {

    @Provides
    internal fun provideApplication(): Application {
        return application
    }

    @Provides
    @ApplicationContext
    internal fun provideContext(): Context {
        return application
    }
}