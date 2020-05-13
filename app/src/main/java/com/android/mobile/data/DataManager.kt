package com.android.mobile.data

import com.android.mobile.data.remote.GithubApi
import com.po.kemon.data.model.Contributor
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject
constructor(private val githubApi: GithubApi) {

    fun getContributors(): Single<List<Contributor>> {
        return githubApi.getContributors()
                .toObservable()
                .flatMapIterable { it }
                .map { it }
                .toList()
    }

}