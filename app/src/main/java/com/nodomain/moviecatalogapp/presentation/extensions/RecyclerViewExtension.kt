package com.nodomain.moviecatalogapp.presentation.extensions


import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView


fun RecyclerView.setOnLastItemReachAction(action: () -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {

        var lastItemReached = false

        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            if (adapter == null) return

            val lastVisibleItemPosition = when (layoutManager) {
                is LinearLayoutManager -> (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                is GridLayoutManager -> (layoutManager as GridLayoutManager).findLastVisibleItemPosition()
                else -> return
            }

            val lastItemIsVisible = lastVisibleItemPosition == adapter.itemCount - 1

            if (lastItemIsVisible) {
                if (!lastItemReached) {
                    lastItemReached = true
                    action()
                }
            } else
                lastItemReached = false
        }
    })
}