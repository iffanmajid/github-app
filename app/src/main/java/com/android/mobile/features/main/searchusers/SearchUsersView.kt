package com.po.kemon.features.main

import com.android.mobile.data.model.Item
import com.android.mobile.features.base.MvpView
import com.po.kemon.data.model.Contributor


interface SearchUsersView : MvpView {

    fun showUsers(users: List<Contributor>)
}