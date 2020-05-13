package com.po.kemon.features.main

import com.android.mobile.features.base.MvpView
import com.po.kemon.data.model.Contributor


interface ContributorsView : MvpView {

    fun showContributors(contributors: List<Contributor>)
}