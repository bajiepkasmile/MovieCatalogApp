package com.nodomain.moviecatalogapp.presentation.extensions


import android.support.design.widget.FloatingActionButton


fun FloatingActionButton.hide(onAnimationEndAction: () -> Unit) {
    hide(object : FloatingActionButton.OnVisibilityChangedListener() {
        override fun onHidden(fab: FloatingActionButton?) = onAnimationEndAction()
    })
}