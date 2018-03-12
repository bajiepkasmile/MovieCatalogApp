package com.nodomain.moviecatalogapp.di.modules.data.datasources.local.general.impls


import android.arch.persistence.room.Room
import android.content.Context
import com.nodomain.moviecatalogapp.data.datasources.local.general.DATABASE_NAME
import com.nodomain.moviecatalogapp.data.datasources.local.general.impls.room.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context) =
            Room.databaseBuilder(context, Database::class.java, DATABASE_NAME).build()
}