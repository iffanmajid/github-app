package com.android.mobile.features.main.contributors

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.mobile.R
import com.android.mobile.features.base.BaseFragment
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
class ContributorsFragment : BaseFragment(), ContributorsView {

    @Inject lateinit var contributorsAdapter: ContributorsAdapter
    @Inject lateinit var contributorsPresenter: ContributorsPresenter

    override fun layoutId() = R.layout.fragment_contributors

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent().inject(this)
        contributorsPresenter.attachView(this)

        contributorsPresenter.getContributors()
    }

    override fun onDestroy() {
        contributorsPresenter.detachView()
        super.onDestroy()
    }
    override fun showContributors(contributors: List<Contributor>) {
        rvUsers?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = contributorsAdapter
        }
        contributorsAdapter.apply {
            setContributors(contributors)
            notifyDataSetChanged()
        }
        rvUsers?.visible()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            progressBar?.visible()
            errorView?.gone()
            rvUsers?.gone()
        } else {
            progressBar?.gone()
            errorView?.gone()
            rvUsers?.visible()
        }
    }

    override fun showError(error: Throwable) {
        progressBar?.gone()
        errorView?.visible()
        rvUsers?.gone()
    }
}