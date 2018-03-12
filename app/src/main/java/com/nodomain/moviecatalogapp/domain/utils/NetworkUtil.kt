package com.nodomain.moviecatalogapp.domain.utils


import android.net.ConnectivityManager
import javax.inject.Inject


class NetworkUtil @Inject constructor(private val connectivityManager: ConnectivityManager) {

    fun isNetworkAvailable(): Boolean {
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}