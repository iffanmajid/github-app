package com.android.mobile.features.main.repositories

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.mobile.R
import com.android.mobile.data.model.Item
import com.android.mobile.features.base.BaseFragment
import com.android.mobile.features.common.ErrorView
import com.android.mobile.util.gone
import com.android.mobile.util.rx.RxSearchObservable
import com.android.mobile.util.visible
import com.po.kemon.features.main.RepositoriesAdapter
import com.po.kemon.features.main.RepositoriesView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.fragment_repositories.*
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Iffan on 13/05/20.
 */
class RepositoriesFragment : BaseFragment(), RepositoriesView, ErrorView.ErrorListener {

    @Inject lateinit var repositoriesAdapter: RepositoriesAdapter
    @Inject lateinit var repositoriesPresenter: RepositoriesPresenter

    override fun layoutId() = R.layout.fragment_repositories

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentComponent().inject(this)
        repositoriesPresenter.attachView(this)
        viewError?.setErrorListener(this)

        rvRepositories?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = repositoriesAdapter
        }

        RxSearchObservable.fromView(svRepositories)
                .debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.length > 2) {
                        repositoriesPresenter.getContributors(it)
                    } else if (it.isEmpty()) {
                        repositoriesAdapter.apply {
                            clearRepositories()
                            notifyDataSetChanged()
                        }
                    }
                 }

        val closeButton = svRepositories?.findViewById<ImageView>(R.id.search_close_btn)
        closeButton?.setOnClickListener {
            svRepositories?.setQuery("", false)
            repositoriesAdapter.apply {
                clearRepositories()
                notifyDataSetChanged()
            }
        }

    }
    override fun showRepositories(repositories: List<Item>) {
        repositoriesAdapter.apply {
            setRepositories(repositories)
            notifyDataSetChanged()
        }
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            progressBar?.visible()
            errorView?.gone()
            rvRepositories?.gone()
        } else {
            progressBar?.gone()
            errorView?.gone()
            rvRepositories.visible()
        }
    }

    override fun showError(error: Throwable) {
        progressBar?.gone()
        errorView?.visible()
        rvRepositories?.gone()
    }

    override fun onReloadData() {

    }
}