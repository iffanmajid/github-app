package com.android.mobile.injection.component

import android.app.Application
import android.content.Context
import dagger.Component
import com.android.mobile.data.DataManager
import com.android.mobile.data.remote.GithubApi
import com.android.mobile.injection.ApplicationContext
import com.android.mobile.injection.module.AppModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun dataManager(): DataManager

    fun githubApi(): GithubApi
}
