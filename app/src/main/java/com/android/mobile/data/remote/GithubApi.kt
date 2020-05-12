package com.android.mobile.data.remote


import com.po.kemon.data.model.ContributorsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface GithubApi {
    @GET("repos/square/retrofit/contributors")
    fun getContributors(): Single<ContributorsResponse>

}
