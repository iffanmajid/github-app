package com.android.mobile.data.remote


import com.android.mobile.data.model.RepositoriesResponse
import com.android.mobile.data.model.SearchedUsersResponse
import com.po.kemon.data.model.Contributor
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {
    @GET("repos/square/retrofit/contributors")
    fun getContributors(): Single<List<Contributor>>

    @GET("search/repositories")
    fun getRepositories(@Query("q") query: String): Single<RepositoriesResponse>

    @GET("/search/users")
    fun searchUsers(@Query("q") query: String, @Query("page") page: Int): Single<SearchedUsersResponse>
}
