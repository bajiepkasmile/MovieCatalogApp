package com.nodomain.moviecatalogapp.presentation.extensions


import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


fun ViewGroup?.inflate(@LayoutRes resource: Int): View {
    return LayoutInflater.from(this!!.context).inflate(resource, this, false)
}