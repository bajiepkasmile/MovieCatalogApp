package com.nodomain.moviecatalogapp.di.modules.domain


import android.content.Context
import android.net.ConnectivityManager
import android.os.Handler
import dagger.Module
import dagger.Provides
import org.greenrobot.eventbus.EventBus
import java.util.concurrent.Executors
import javax.inject.Singleton


@Module
class DomainModule {

    @Singleton
    @Provides
    fun provideExecutorService() = Executors.newFixedThreadPool(4)

    @Singleton
    @Provides
    fun provideMainThreadHandler() = Handler()

    @Provides
    fun provideEventBus() = EventBus.getDefault()

    @Provides
    fun provideConnectivityManager(context: Context): ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
}