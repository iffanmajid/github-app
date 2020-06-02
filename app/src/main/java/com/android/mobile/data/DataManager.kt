package com.android.mobile.data

import com.android.mobile.data.model.Item
import com.android.mobile.data.remote.GithubApi
import com.po.kemon.data.model.Contributor
import io.reactivex.Single
import retrofit2.http.Query
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

    fun getRepositories(query: String): Single<List<Item>> {
        return githubApi.getRepositories(query)
                .toObservable()
                .flatMapIterable { it.items }
                .map { it }
                .toList()
    }

    fun searchUsers(query: String, page: Int): Single<List<Contributor>> {
        return githubApi.searchUsers(query, page)
                .toObservable()
                .flatMapIterable { it.items }
                .map { it }
                .toList()
    }
}