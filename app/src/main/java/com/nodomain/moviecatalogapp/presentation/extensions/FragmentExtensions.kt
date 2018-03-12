package com.nodomain.moviecatalogapp.presentation.extensions


import android.support.v4.app.Fragment
import com.nodomain.moviecatalogapp.App


val Fragment.app: App
    get() = activity.application as App