package com.nodomain.moviecatalogapp.presentation.extensions


import android.support.annotation.DrawableRes
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


fun ImageView.setImageUrl(url: String, @DrawableRes placeholderResId: Int, onCompleteAction: () -> Unit) {
    Picasso.with(context)
            .load(url)
            .placeholder(placeholderResId)
            .into(this, object : Callback {
                override fun onSuccess() = onCompleteAction()
                override fun onError() {}
            })
}