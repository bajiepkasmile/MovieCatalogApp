package com.nodomain.moviecatalogapp


import android.app.Application
import com.nodomain.moviecatalogapp.di.components.DaggerApplicationComponent
import com.nodomain.moviecatalogapp.di.modules.ApplicationModule


class App : Application() {

    val applicationComponent by lazy {
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        applicationComponent.inject(this)  //для инициализации applicationComponent
    }
}