package com.nodomain.moviecatalogapp.presentation.extensions


import android.view.View
import android.view.ViewTreeObserver


private const val DEFAULT_ANIMATION_DURATION: Long = 200


fun View.setOnLayoutChangeAction(action: () -> Unit) {
    addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ -> action()}
}

fun View.setDisposableOnGlobalLayoutAction(action: () -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            viewTreeObserver.removeOnGlobalLayoutListener(this)
            action()
        }
    })
}

fun View.showByScale() {
    scaleX = 0f
    scaleY = 0f
    visibility = View.VISIBLE

    animate()
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(DEFAULT_ANIMATION_DURATION)
            .start()
}

fun View.showByAlpha() {
    alpha = 0f
    visibility = View.VISIBLE

    animate()
            .alpha(1f)
            .setDuration(DEFAULT_ANIMATION_DURATION)
            .start()
}

fun View.showByScaleAndAlpha() {
    scaleX = 0f
    scaleY = 0f
    alpha = 0f
    visibility = View.VISIBLE

    animate()
            .scaleX(1f)
            .scaleY(1f)
            .alpha(1f)
            .setDuration(DEFAULT_ANIMATION_DURATION)
            .start()
}

fun View.showByTranslationAndAlpha() {
    alpha = 0f
    translationY = 150f
    visibility = View.VISIBLE

    animate()
            .alpha(1f)
            .translationY(0f)
            .setDuration(DEFAULT_ANIMATION_DURATION)
            .start()
}

fun View.hideByScale(finalVisibility: Int = View.GONE, onAnimationEndAction: () -> Unit = {}) {
    animate()
            .scaleX(0f)
            .scaleY(0f)
            .setDuration(DEFAULT_ANIMATION_DURATION)
            .setDisposableOnAnimationEndAction{
                visibility = finalVisibility
                onAnimationEndAction()
            }
            .start()
}

fun View.hideByAlpha(finalVisibility: Int = View.GONE, onAnimationEndAction: () -> Unit = {}) {
    animate()
            .alpha(0f)
            .setDuration(DEFAULT_ANIMATION_DURATION)
            .setDisposableOnAnimationEndAction{
                visibility = finalVisibility
                onAnimationEndAction()
            }
            .start()
}

fun View.hideByScaleAndAlpha(finalVisibility: Int = View.GONE, onAnimationEndAction: () -> Unit = {}) {
    animate()
            .scaleX(0f)
            .scaleY(0f)
            .alpha(0f)
            .setDuration(DEFAULT_ANIMATION_DURATION)
            .setDisposableOnAnimationEndAction{
                visibility = finalVisibility
                onAnimationEndAction()
            }
            .start()
}

fun View.hideByTranslationAndAlpha(finalVisibility: Int = View.GONE, onAnimationEndAction: () -> Unit = {}) {
    animate()
            .alpha(0f)
            .translationY(150f)
            .setDuration(DEFAULT_ANIMATION_DURATION)
            .setDisposableOnAnimationEndAction{
                visibility = finalVisibility
                onAnimationEndAction()
            }
            .start()
}