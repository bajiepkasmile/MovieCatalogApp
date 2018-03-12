package com.nodomain.moviecatalogapp.presentation.extensions


import android.support.design.widget.Snackbar


fun Snackbar.setOnDismissAction(action: () -> Unit): Snackbar {
    return addCallback(object : Snackbar.Callback() {
        override fun onDismissed(transientBottomBar: Snackbar?, event: Int) = action()
    })
}