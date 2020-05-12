package com.po.kemon.features.main

import android.os.Bundle
import com.android.mobile.R
import com.android.mobile.features.base.BaseActivity
import com.android.mobile.util.gone
import com.android.mobile.util.visible
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber


class MainActivity : BaseActivity(), MainMvpView {

    companion object {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent().inject(this)

        setSupportActionBar(main_toolbar)
    }

    override fun layoutId() = R.layout.activity_main

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun showPokemon(pokemon: List<String>) {

    }

    override fun showProgress(show: Boolean) {

    }

    override fun showError(error: Throwable) {
        recyclerPokemon?.gone()
        swipeToRefresh?.gone()
        viewError?.visible()
        Timber.e(error, "There was an error retrieving the pokemon")
    }
}