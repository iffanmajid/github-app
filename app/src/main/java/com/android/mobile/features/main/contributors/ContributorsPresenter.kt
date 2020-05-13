package com.android.mobile.features.main.contributors

import com.android.mobile.data.DataManager
import com.android.mobile.features.base.BasePresenter
import com.android.mobile.injection.ConfigPersistent
import com.android.mobile.util.rx.scheduler.SchedulerUtils
import com.po.kemon.features.main.ContributorsView
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Iffan on 13/05/20.
 */
@ConfigPersistent
class ContributorsPresenter @Inject
constructor(private val dataManager: DataManager) : BasePresenter<ContributorsView>() {
    fun getContributors() {
        checkViewAttached()
        mvpView?.showProgress(true)
        dataManager.getContributors()
                .compose(SchedulerUtils.ioToMain())
                .subscribe({t ->
                    mvpView?.apply {
                        showProgress(false)
                        showContributors(t)

                    }
                }) { mvpView?.apply {
                    showProgress(false)
                    showError(it)
                }}
    }
}