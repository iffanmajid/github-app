package com.android.mobile.injection.module

import com.android.mobile.data.remote.GithubApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by shivam on 8/7/17.
 */
@Module(includes = arrayOf(NetworkModule::class))
class ApiModule {
    @Provides
    @Singleton
    internal fun providePokemonApi(retrofit: Retrofit): GithubApi =
            retrofit.create(GithubApi::class.java)
}