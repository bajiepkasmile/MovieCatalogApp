package com.nodomain.moviecatalogapp.presentation.utils


import android.os.Handler
import java.util.*
import javax.inject.Inject


class TimerUtil @Inject constructor(private val mainThreadHandler: Handler) {

    fun schedule(delay: Long, action: () -> Unit): Timer {
        val timer = Timer()

        timer.schedule(object : TimerTask() {
            override fun run() {
               mainThreadHandler.post(action)
            }
        }, delay)

        return timer
    }
}