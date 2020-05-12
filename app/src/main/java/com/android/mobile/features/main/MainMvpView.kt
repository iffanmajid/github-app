package com.po.kemon.features.main

import com.android.mobile.features.base.MvpView


interface MainMvpView : MvpView {

    fun showPokemon(pokemon: List<String>)

    fun showProgress(show: Boolean)

    fun showError(error: Throwable)

}