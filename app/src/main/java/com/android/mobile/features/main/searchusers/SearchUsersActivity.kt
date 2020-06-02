package com.android.mobile.features.main.searchusers

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.mobile.R
import com.android.mobile.features.base.BaseActivity
import com.android.mobile.util.EndlessRecyclerViewScrollListener
import com.android.mobile.util.gone
import com.android.mobile.util.rx.RxSearchObservable
import com.android.mobile.util.visible
import com.google.android.material.snackbar.Snackbar
import com.po.kemon.data.model.Contributor
import com.po.kemon.features.main.ContributorsAdapter
import com.po.kemon.features.main.SearchUsersView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_users.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Iffan on 13/05/20.
 */
class SearchUsersActivity : BaseActivity(), SearchUsersView {

    @Inject lateinit var contributorsAdapter: ContributorsAdapter
    @Inject lateinit var searchUsersPresenter: SearchUsersPresenter

    private var contributors: MutableList<Contributor> = mutableListOf()
    lateinit var scrollListener: EndlessRecyclerViewScrollListener

    private var lastRequestedPage = 1
    private var lastRequestedQuery = ""

    override fun layoutId() = R.layout.activity_users

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent().inject(this)

        searchUsersPresenter.attachView(this)

        val linearLayoutManager = LinearLayoutManager(this)
        scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                lastRequestedPage += 1
                searchUsersPresenter.getContributors(lastRequestedQuery, lastRequestedPage)
            }
        }

        rvRepositories?.apply {
            layoutManager = linearLayoutManager
            adapter = contributorsAdapter
            addOnScrollListener(scrollListener)
        }

        RxSearchObservable.fromView(svRepositories)
                .debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.length > 2) {
                        lastRequestedQuery = it
                        searchUsersPresenter.getContributors(lastRequestedQuery, lastRequestedPage)
                    } else if (it.isEmpty()) {
                        resetUsers()
                    }
                }

        val closeButton = svRepositories?.findViewById<ImageView>(R.id.search_close_btn)
        closeButton?.setOnClickListener {
            resetUsers()
            svRepositories?.setQuery("", false)
        }

        svRepositories?.requestFocus()
    }

    private fun resetUsers() {
        contributors.clear()
        contributorsAdapter.apply {
            clearContributors()
            notifyDataSetChanged()
        }
        scrollListener.resetState()
        lastRequestedPage = 1
        lastRequestedQuery = ""
    }

    override fun showUsers(users: List<Contributor>) {
        contributors.addAll(users)
        contributorsAdapter.apply {
            setContributors(users.toMutableList())
            notifyDataSetChanged()
        }
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            if (contributors.isEmpty()) {
                progressBar?.visible()
                rvRepositories?.gone()
            }
        } else {
            progressBar?.gone()
            rvRepositories?.visible()
        }
    }

    override fun showError(error: Throwable) {
        progressBar?.gone()
        rvRepositories?.gone()
        Snackbar.make(
                layoutSearch,
                error.message.toString(),
                Snackbar.LENGTH_INDEFINITE)
                .setAction(
                        "Retry") { searchUsersPresenter.getContributors(lastRequestedQuery, lastRequestedPage) }.show()
    }
}