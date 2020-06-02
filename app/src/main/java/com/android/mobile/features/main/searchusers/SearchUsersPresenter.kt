package com.android.mobile.features.main.searchusers

import com.android.mobile.data.DataManager
import com.android.mobile.features.base.BasePresenter
import com.android.mobile.injection.ConfigPersistent
import com.android.mobile.util.rx.scheduler.SchedulerUtils
import com.po.kemon.features.main.SearchUsersView
import javax.inject.Inject

/**
 * Created by Iffan on 13/05/20.
 */
@ConfigPersistent
class SearchUsersPresenter @Inject
constructor(private val dataManager: DataManager) : BasePresenter<SearchUsersView>() {
    fun getContributors(query: String, page: Int) {
        checkViewAttached()
        mvpView?.showProgress(true)
        dataManager.searchUsers(query, page)
                .compose(SchedulerUtils.ioToMain())
                .subscribe({t ->
                    mvpView?.apply {
                        showProgress(false)
                        showUsers(t)
                    }
                }) { mvpView?.apply {
                    showProgress(false)
                    showError(it)
                }}
    }
}