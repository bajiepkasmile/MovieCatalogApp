package com.nodomain.moviecatalogapp.presentation.mvp.base


interface MvpPresenter<in V : MvpView> {

    fun attachMvpView(mvpView: V)

    fun detachMvpView()
}