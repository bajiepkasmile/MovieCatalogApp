package com.nodomain.moviecatalogapp.presentation.ui.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.nodomain.moviecatalogapp.presentation.mvp.base.MvpPresenter
import com.nodomain.moviecatalogapp.presentation.mvp.base.MvpView
import javax.inject.Inject


abstract class MvpFragment<V : MvpView, P : MvpPresenter<V>> : Fragment(), MvpView {

    @Inject
    protected lateinit var mvpPresenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {
            mvpPresenter.attachMvpView(this as V)
        } catch (e: ClassCastException) {
            throw ClassCastException(this::class.toString()
                    + " does not implement the required *MvpView interface")
        }
    }

    override fun onDestroyView() {
        mvpPresenter.detachMvpView()
        super.onDestroyView()
    }
}