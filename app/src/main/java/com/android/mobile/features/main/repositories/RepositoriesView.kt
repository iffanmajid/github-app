package com.po.kemon.features.main

import com.android.mobile.data.model.Item
import com.android.mobile.features.base.MvpView
import com.po.kemon.data.model.Contributor


interface RepositoriesView : MvpView {

    fun showRepositories(repositories: List<Item>)
}