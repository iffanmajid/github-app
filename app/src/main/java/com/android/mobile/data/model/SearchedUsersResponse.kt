package com.android.mobile.data.model

import com.po.kemon.data.model.Contributor
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Iffan on 01/06/20.
 */
@JsonClass(generateAdapter = true)
data class SearchedUsersResponse(
        @Json(name = "total_count")
        val totalCount: Int?,
        @Json(name = "incomplete_results")
        val incompleteResults: Boolean?,
        @Json(name = "items")
        val items: List<Contributor>?
)