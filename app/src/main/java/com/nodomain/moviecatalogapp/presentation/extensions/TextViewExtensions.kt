package com.nodomain.moviecatalogapp.presentation.extensions


import android.support.annotation.DrawableRes
import android.widget.TextView


fun TextView.setDrawableTop(@DrawableRes drawableResId: Int) {
    setCompoundDrawablesWithIntrinsicBounds(0, drawableResId, 0, 0)
}