package com.nodomain.moviecatalogapp.data.datasources.local.general.impls.room


import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Database
import com.nodomain.moviecatalogapp.data.datasources.local.specific.movies.impls.roomimpl.room.MovieDao
import com.nodomain.moviecatalogapp.data.datasources.local.specific.movies.impls.roomimpl.room.MovieDbo


@Database(entities = [(MovieDbo::class)], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}