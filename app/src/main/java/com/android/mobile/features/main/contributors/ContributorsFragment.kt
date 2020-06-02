package com.android.mobile.features.main.contributors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.mobile.R
import com.android.mobile.features.base.BaseFragment
import com.android.mobile.features.common.ErrorView
import com.android.mobile.util.gone
import com.android.mobile.util.visible
import com.po.kemon.data.model.Contributor
import com.po.kemon.features.main.ContributorsAdapter
import com.po.kemon.features.main.ContributorsView
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.fragment_contributors.*
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Iffan on 13/05/20.
 */
class ContributorsFragment : BaseFragment(), ContributorsView, ErrorView.ErrorListener {

    @Inject lateinit var contributorsAdapter: ContributorsAdapter
    @Inject lateinit var contributorsPresenter: ContributorsPresenter

    override fun layoutId() = R.layout.fragment_contributors

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentComponent().inject(this)
        contributorsPresenter.attachView(this)
        viewError?.setErrorListener(this)

        rvUsers?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = contributorsAdapter
        }
        contributorsPresenter.getContributors()
    }
    override fun onDestroy() {
        contributorsPresenter.detachView()
        super.onDestroy()
    }
    override fun showContributors(contributors: List<Contributor>) {
        contributorsAdapter.apply {
            setContributors(contributors.toMutableList())
            notifyDataSetChanged()
        }
        rvUsers.visible()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            progressBar?.visible()
            errorView?.gone()
            rvUsers?.gone()
        } else {
            progressBar?.gone()
            errorView?.gone()
            rvUsers.visible()
        }
    }

    override fun showError(error: Throwable) {
        progressBar?.gone()
        errorView?.visible()
        rvUsers?.gone()
    }

    override fun onReloadData() {
        contributorsPresenter.getContributors()
    }
}