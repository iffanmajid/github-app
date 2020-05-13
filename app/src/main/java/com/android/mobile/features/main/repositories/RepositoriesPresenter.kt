package com.android.mobile.features.main.repositories

import com.android.mobile.data.DataManager
import com.android.mobile.features.base.BasePresenter
import com.android.mobile.injection.ConfigPersistent
import com.android.mobile.util.rx.scheduler.SchedulerUtils
import com.po.kemon.features.main.RepositoriesView
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Iffan on 13/05/20.
 */
@ConfigPersistent
class RepositoriesPresenter @Inject
constructor(private val dataManager: DataManager) : BasePresenter<RepositoriesView>() {
    fun getContributors(query: String) {
        checkViewAttached()
        mvpView?.showProgress(true)
        dataManager.getRepositories(query)
                .compose(SchedulerUtils.ioToMain())
                .subscribe({t ->
                    mvpView?.apply {
                        showProgress(false)
                        showRepositories(t)

                    }
                }) { mvpView?.apply {
                    showProgress(false)
                    showError(it)
                }}
    }
}