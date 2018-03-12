package com.nodomain.moviecatalogapp.presentation.mvp.base


import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.EventBusException


abstract class MvpPresenterImpl<V : MvpView>(private val eventBus: EventBus) : MvpPresenter<V> {

    protected var mvpView: V? = null

    override fun attachMvpView(mvpView: V) {
        this.mvpView = mvpView

        try {
            eventBus.register(this)
        } catch (e: EventBusException) {  //бросается, когда subscriber не имеет подписок на события
            e.printStackTrace()
        }
    }

    override fun detachMvpView() {
        eventBus.unregister(this)
        mvpView = null
    }

    protected fun removeStickyEvent(event: Any?) = eventBus.removeStickyEvent(event)
}