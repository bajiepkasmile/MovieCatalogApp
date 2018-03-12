package com.nodomain.moviecatalogapp.domain.interactors


import android.os.Handler
import org.greenrobot.eventbus.EventBus
import java.util.concurrent.ExecutorService


abstract class Interactor<A>(
        private val executorService: ExecutorService,
        private val mainThreadHandler: Handler,
        private val eventBus: EventBus) {

    abstract fun execute(args: A)

    protected fun inBackground(action: () -> Unit) = executorService.execute(action)

    protected fun onMainThread(action: () -> Unit) = mainThreadHandler.post(action)

    protected fun postStickyEvent(event: Any) = eventBus.postSticky(event)
}