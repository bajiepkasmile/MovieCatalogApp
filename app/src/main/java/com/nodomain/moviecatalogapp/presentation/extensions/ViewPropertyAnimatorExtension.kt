package com.nodomain.moviecatalogapp.presentation.extensions


import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.ViewPropertyAnimator


fun ViewPropertyAnimator.setDisposableOnAnimationEndAction(action: () -> Unit): ViewPropertyAnimator {
    return setListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator?) {
            setListener(null)
            action()
        }
    })
}